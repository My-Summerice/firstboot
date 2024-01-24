package org.example.firstboot.entity;

import lombok.Data;

@Data
public class User {
    private long id;

    private String name;

    private int age;

    private int gender;

    private String password;
}

