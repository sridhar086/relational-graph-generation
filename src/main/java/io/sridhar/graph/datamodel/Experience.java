package io.sridhar.graph.datamodel;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Date;

@Jacksonized
@ToString
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Experience implements Serializable {

    @JsonProperty("company")
    @NonNull
    String company;
    @JsonProperty("start")
    @NonNull
    Date start;
    @JsonProperty("end")
    Date end;

}
