package io.sridhar.graph.component;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComponentTest {

    @Test
    public void testComponent() throws URISyntaxException {

        URL fileUrl = getClass().getClassLoader().getResource("smalldataformatted.jsonl");
        Component component = new Component(fileUrl.toURI());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        try {
            System.setOut(printStream);
            component.processData();
            String capturedOutput = outputStream.toString().trim();
            String result = "Sherye Friend, Cos Curr, nvidia, 749.5714\n" +
                    "Aksel Iglesiaz, Cirilo Reeve, applecart, 422.33334\n" +
                    "Boyd Jahn, Sabine Crollman, google, 308.85715\n" +
                    "Total Records: 3";
            assertEquals(result, capturedOutput);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
