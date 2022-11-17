package com.example.testmaker.entety;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
    private short number;
    private String description;
    private List<String> answers;
    private List<Integer> indexAnswers;

}
