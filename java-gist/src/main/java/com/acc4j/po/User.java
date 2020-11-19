package com.acc4j.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String name;
    private String sex;
    private int age;
    private String address;

    public User(String name) {
        this.name = name;
        System.out.println("create new user[ " + name + " ]");
    }

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }
}
