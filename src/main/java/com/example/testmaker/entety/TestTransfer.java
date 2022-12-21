package com.example.testmaker.entety;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestTransfer {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Quest {
        @JsonProperty("number")
        private short number;
        @JsonProperty("description")
        private String description;
        @JsonProperty("answers")
        private List<String> answers;

    }

    @JsonProperty("quests")
    private final List<Quest> quests = new ArrayList<>();
    @JsonProperty("time")
    private int time;
    @JsonProperty("countQuest")
    private int countQuest;

    public static TestTransfer mapper(Test test) {
        TestTransfer tr = new TestTransfer();
        tr.time = test.getTime();
        tr.countQuest = test.getCountQuest();
        for (com.example.testmaker.entety.Quest quest : test.getQuests()) {
            tr.quests.add(new Quest(quest.getNumber(), quest.getDescription(), quest.getAnswers()));
        }
        return tr;
    }
}
