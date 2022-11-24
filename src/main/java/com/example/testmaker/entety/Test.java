package com.example.testmaker.entety;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Test {
    private UUID id = UUID.randomUUID();
    private int countQuest;
    private String subjectName;
    private int time;
    private List<Quest> quests = new ArrayList<>();
    private LocalDateTime dateCreated;

    @Override
    public String toString() {
        String quest = id.toString() + "\n";
        for (Quest q :
                quests) {
            quest += "Вопрос" + "\n";
            quest += q.getDescription() + "\n";
            quest += "Варианты ответа" + "\n";
            quest += q.getAnswers().get(0) + "\n";
            quest += q.getAnswers().get(1) + "\n";
            quest += q.getAnswers().get(2) + "\n";
            quest += q.getAnswers().get(3) + "\n";
        }
        return "Тест" + "\n" +
                "Количество вопросов - " + countQuest + "\n" +
                "Название дисциплины - " + subjectName + "\n" +
                "Время на выполнение теста (в минутах) - " + time + "\n" +
                quest + "-" + "\n" +
                "Дата и время создания " + dateCreated;
    }

    public String answerToString() {
        String res = id.toString() + "\n";
        for (Quest q :
                quests) {
            for (Integer i :
                    q.getIndexAnswers()) {
                res += i + " ";
            }
            res+="\n";
        }
        return res;
    }
}
