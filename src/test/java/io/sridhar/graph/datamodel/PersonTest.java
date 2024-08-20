package io.sridhar.graph.datamodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class PersonTest {


    @Test
    public void testSerializablePerson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String data = "{\"first\":\"Lenette\",\"last\":\"Prophet\",\"experience\":[{\"company\":\"applecart\",\"start\":\"2005-08-07\",\"end\":\"2007-08-14\"},{\"company\":\"google\",\"start\":\"2008-03-27\",\"end\":\"2010-06-17\"},{\"company\":\"nvidia\",\"start\":\"2012-08-11\",\"end\":\"2016-05-17\"}]}";
        objectMapper.readValue(data, Person.class);
    }


}
