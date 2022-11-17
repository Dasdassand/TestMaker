package com.example.testmaker.entety;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Test {
    private int countQuest;
    private int countAnswer;
    private String subjectName;
    private int time;
    private List<Quest> quests = new ArrayList<>();
    private LocalDateTime dateCreated;
}
