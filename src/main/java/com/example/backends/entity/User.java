package com.example.backends.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {

    private String userId;
    private String name;
    private String mail;
    private UserState userState;

    public enum UserState {
        VALID,
        EXPIRE,
        UNSUBSCRIBE
    }
}
