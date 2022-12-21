package com.example.testmaker.entety;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;

    public User(String s, String s1) {
        username = s;
        password = s1;
    }

    public boolean equals(User user) {
        //System.out.println( getClass().getResource("title.png").getPath());
        return this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());
    }
}
