package com.example.testmaker.entety;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Test {
    private UUID id = UUID.randomUUID();
    private String idTeacher;
    private String idPlatoon;
    private String idSubject;
    private LocalDateTime date;
    private String path;
    private final List<Quest> quests = new ArrayList<>();
    private int time;
    private int countQuest;

}
