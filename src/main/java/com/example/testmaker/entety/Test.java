package com.example.testmaker.entety;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Test {
    private int countQuest;
    private String teacherName;
    private String subjectName;
    private int time;
    private List<Quest> quests;
    private LocalDate dateCreated;
}
