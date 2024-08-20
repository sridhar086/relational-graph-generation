package io.sridhar.graph;

import org.junit.jupiter.api.Test;


public class MainTest {

    @Test
    public void testMain() {
        String[] args = {"test-dataset/smalldataformatted.jsonl"};
        Main.main(args);
    }
}
