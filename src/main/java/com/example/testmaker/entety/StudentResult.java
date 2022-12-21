package com.example.testmaker.entety;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResult {
    private int id;
    private String username;
    private int count;

    public StudentResult(int id) {
        this.id = id;
    }
}
