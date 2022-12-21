package com.example.testmaker.server;

import com.example.testmaker.entety.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import io.netty.channel.Channel;

@Data
public class PersonChanel {
    private User user;
    private String channel;

    public PersonChanel(User user, String channel) {
        this.user = user;
        this.channel = channel;
    }
}
