package com.example.testmaker.entety;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
    @JsonProperty("number")
    private short number;
    @JsonProperty("description")
    private String description;
    @JsonProperty("answers")
    private List<String> answers;
    @JsonProperty("indexAnswers")
    private List<Integer> indexAnswers;

}
