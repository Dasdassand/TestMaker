package com.example.testmaker.entety;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResult {
    private User user;
    private List<Answers> answers;
}
