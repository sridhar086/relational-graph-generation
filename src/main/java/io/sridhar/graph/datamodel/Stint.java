package io.sridhar.graph.datamodel;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;


@Jacksonized
@ToString
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Stint {

    Person person;
    Date start;
    Date end;

}
