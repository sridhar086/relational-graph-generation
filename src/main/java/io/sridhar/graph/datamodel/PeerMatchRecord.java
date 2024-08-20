package io.sridhar.graph.datamodel;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Jacksonized
@ToString
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PeerMatchRecord implements Serializable {

    String companyName;
    String employeePair;
    Float score;
    boolean current;

}
