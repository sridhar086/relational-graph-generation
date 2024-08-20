package io.sridhar.graph.datamodel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Jacksonized
@ToString
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Person implements Serializable {

    @JsonProperty("first")
    @NonNull
    String first;
    @JsonProperty("last")
    @NonNull
    String last;
    @JsonProperty("experience")
    @NonNull
    List<Experience> experience;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(first, person.first) && Objects.equals(last, person.last);
    }
}
