package com.example.testmaker.data;

import com.example.testmaker.entety.Platoon;
import com.example.testmaker.entety.Quest;
import com.example.testmaker.entety.Test;
import com.example.testmaker.entety.User;

import java.util.ArrayList;
import java.util.List;


public class TemporaryMemory {
    public static String path;
    public static String filename = "test";
    public static Integer countQuest;
    public static List<Platoon> platoons = new ArrayList<>();
    public static Quest quest = new Quest();
    public static Test test = new Test();
    public static User user = new User();

}
