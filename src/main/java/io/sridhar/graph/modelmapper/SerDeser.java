package io.sridhar.graph.modelmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sridhar.graph.datamodel.Person;

import java.io.IOException;

public class SerDeser {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static Person deserialize(String data) throws IOException {
        return objectMapper.readValue(data, Person.class);
    }

}
