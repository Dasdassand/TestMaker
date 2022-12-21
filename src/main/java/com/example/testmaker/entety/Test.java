package com.example.testmaker.entety;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @JsonIgnore
    private UUID id = UUID.randomUUID();
    @JsonIgnore
    private String idTeacher;
    @JsonIgnore
    private String idPlatoon;
    @JsonIgnore
    private String idSubject;
    @JsonProperty("date")
    private LocalDateTime date;
    @JsonProperty("quests")
    private final List<Quest> quests = new ArrayList<>();
    @JsonProperty("time")
    private int time;
    @JsonProperty("countQuest")
    private int countQuest;

    public String getId() {
        return id.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(id.toString() + "\n" + "Дата и время создания  - " + date + "\n" + "Время на выполнение - "
                + time + "\n" + "Количетсво вопросов - " + countQuest + "\n");
        for (Quest q :
                quests) {
            res.append("Вопрос - ").append(q.getDescription()).append("\n").append("Варианты ответов").append("\n");
            for (String s :
                    q.getAnswers()) {
                res.append(s).append("\n");
            }
        }
        return res.toString();
    }
}
