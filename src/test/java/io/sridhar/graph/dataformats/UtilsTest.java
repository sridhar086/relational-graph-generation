package io.sridhar.graph.dataformats;

import io.sridhar.graph.datamodel.PeerMatchRecord;
import io.sridhar.graph.datamodel.Person;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    @Test
    public void serialization() throws ParseException {


        PeerMatchRecord peer = PeerMatchRecord.builder()
                .companyName("google")
                .score(423.0f)
                .employeePair("John Doe, Johny Walker").build();

        String result = Utils.getOutputFormat(peer);

        assertEquals(result, "John Doe, Johny Walker, google, 423.0");

    }

    @Test
    public void getPeerMatchRecordTest() {

        Person p1 = Person.builder().first("John").last("Doe").experience(List.of()).build();
        Person p2 = Person.builder().first("Johny").last("Walker").experience(List.of()).build();

        PeerMatchRecord peerresult = Utils.getPeerMatchRecords(p1, p2, "google", 423.0f);

        PeerMatchRecord peer = PeerMatchRecord.builder()
                .companyName("google")
                .score(423.0f)
                .employeePair("John Doe, Johny Walker").build();

        assertEquals(peer, peerresult);
    }

}
