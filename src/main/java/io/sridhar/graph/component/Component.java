package io.sridhar.graph.component;

import io.sridhar.graph.dataformats.Utils;
import io.sridhar.graph.datamodel.PeerMatchRecord;
import io.sridhar.graph.datamodel.Person;
import io.sridhar.graph.datamodel.Stint;
import io.sridhar.graph.modelmapper.SerDeser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.sridhar.graph.util.DateUtils.overlapDays;
import static io.sridhar.graph.util.DateUtils.overlaps;

public class Component {

    URI fileName;
    File f;

    public Component(URI fileName) {
        this.fileName = fileName;
        f = new File(fileName);
        if(!f.exists() || f.isDirectory()) {
            throw new RuntimeException("Invalid file path, file either doesn't exist or it is a directory: " + fileName);
        }
    }

    // The code works by incrementally reading the records from the file and everytime
    // update company size first
    // update company stint map for easy referencing of existing employee records
    // generate coworker pair for the given person p record comparing with the stints map
    // finally calculate the score, filtering and printing the output
    public void processData() {

        // company name -> size
        HashMap<String, Integer> companySize = new HashMap<>();
        // company name - List (Stint(Person, start, end), ...)
        HashMap<String, List<Stint>> companyToPerson = new HashMap<>();

        // Main List of Objects - This stores the final list
        List<PeerMatchRecord> peers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                Person p = SerDeser.deserialize(line);
                this.updateCompanySize(p, companySize);
                this.updateCompanyStintsMap(p, companyToPerson);
                peers.addAll(this.generateEmployeePairs(p, companyToPerson));
            }
            this.calculateScoreAndPrintRecords(peers, companySize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method updates the company size map
    public void updateCompanySize(Person p, HashMap<String, Integer> companySize) {
        p.getExperience()
                .forEach(experience -> companySize.compute(experience.getCompany(),
                        (companyName, size) -> {
                            if(size == null)
                                return 1;
                            else
                                return size + 1;
                        })

                );
    }

    // This method updates the map of company to stint
    // A stint is defined by start, end and person p working in that company
    private void updateCompanyStintsMap(Person p, HashMap<String, List<Stint>> companyToPerson) {
        p.getExperience()
                .forEach(experience -> companyToPerson.compute(experience.getCompany(),
                        (companyName, personList) -> {
                            if (personList == null) {
                                personList = new ArrayList<>();
                            }
                            personList.add(Stint.builder()
                                    .person(p)
                                    .start(experience.getStart())
                                    .end(experience.getEnd()).build()
                            );
                            return personList;
                        }));
    }



    // This method generates employee pairs by comparing it with previously existing person records
    // This adds PeerMatchRecord to a list
    public List<PeerMatchRecord>  generateEmployeePairs(Person p, HashMap<String, List<Stint>> companyToPerson) {
        return p.getExperience().stream().flatMap(experience ->
                companyToPerson.get(experience.getCompany()).stream()
                        .filter(i -> !i.getPerson().equals(p))
                        .filter(i ->
                                overlaps(i.getStart(), i.getEnd(), experience.getStart(), experience.getEnd())
                        )
                        .map(i -> Utils.getPeerMatchRecords(i.getPerson(),
                                        p,
                                        experience.getCompany(),
                                        overlapDays(i.getStart(), i.getEnd(), experience.getStart(), experience.getEnd())
                                )
                        )
        ).collect(Collectors.toList());
    }


    // This method calculates the average score by calculating overlap-days/company-size
    // It then pulls only 10% of former coworker records
    // It includes all the current coworker records
    public void calculateScoreAndPrintRecords(List<PeerMatchRecord> peers, HashMap<String, Integer> companySize) {

        AtomicInteger totalCount = new AtomicInteger();
        peers.stream()
                .peek(entry -> entry.setScore(entry.getScore() / companySize.get(entry.getCompanyName())))
                .collect(Collectors.groupingBy(
                        PeerMatchRecord::getCompanyName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Stream<PeerMatchRecord> current = list.stream()
                                            .filter(PeerMatchRecord::isCurrent);
                                    List<PeerMatchRecord> former = list.stream()
                                            .filter(i -> !i.isCurrent()).toList();
                                    long count = former.size();
                                    Stream<PeerMatchRecord> filtered =
                                            former.stream()
                                                    .sorted(Comparator
                                                            .comparingDouble(PeerMatchRecord::getScore).reversed())
                                                    .limit((long) Math.ceil(count*0.01));
                                    return Stream.concat(current, filtered);
                                }
                        )
                ))
                .values().stream().flatMap(i -> i)
                .sorted(Comparator.comparingDouble(PeerMatchRecord::getScore).reversed())
                .map(Utils::getOutputFormat)
                .forEach(i -> {
                    totalCount.addAndGet(1);
                    System.out.println(i);
                });
        System.out.println("Total Records: " + totalCount);
    }
}
