package io.sridhar.graph.datamodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class ExperienceTest {

    @Test
    public void testSerializableExperience() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue("{\"company\":\"applecart\",\"start\":\"2005-08-07\",\"end\":\"2007-08-14\"}", Experience.class);
    }
}
