package edu.t1.app;

import lombok.Data;

@Data
public class Empoyee {
    String name;
    Integer age;
    String position;

    public Empoyee(String name, Integer age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }
}
