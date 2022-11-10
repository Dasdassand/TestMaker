package com.example.testmaker.entety;


import lombok.Data;

import java.util.List;

@Data
public class Quest {
    private short number = -1;
    private String description;
    private List<String> quests;
    private int index;

}
