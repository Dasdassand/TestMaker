package com.example.testmaker.entety;

import lombok.Data;

@Data
public class User {
    private String name;
    private String login;
    private String password;
    private boolean isAdmin;
    private Platoon platoon;
}
