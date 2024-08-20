package io.sridhar.graph.dataformats;

import io.sridhar.graph.datamodel.PeerMatchRecord;
import io.sridhar.graph.datamodel.Person;

public class Utils {

    //This method returns a string in the necessary output format
    public static String getOutputFormat(PeerMatchRecord peer) {
        String record =  "%s, %s, %s".formatted(
                peer.getEmployeePair(), peer.getCompanyName(), peer.getScore());
        return peer.isCurrent() ? "%s (current)".formatted(record) : record;
    }

    // This method generated an object given two person, company name and score
    // Note: here score is overlap days only
    public static PeerMatchRecord getPeerMatchRecords(Person p1, Person p2, String companyName, Float score) {
        PeerMatchRecord.PeerMatchRecordBuilder builder = PeerMatchRecord.builder()
                .companyName(companyName)
                .employeePair("%s %s, %s %s".formatted(p1.getFirst(), p1.getLast(), p2.getFirst(), p2.getLast()))
                .score(Math.abs(score));
        if(score < 0)
            builder.current(true);
        return builder.build();
    }

}
