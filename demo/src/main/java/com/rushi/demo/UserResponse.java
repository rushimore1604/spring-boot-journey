package com.rushi.demo;

public class UserResponse {

    private String message;
    private String name;
    private int age;

    public UserResponse(String message, String name, int age) {
        this.message = message;
        this.name = name;
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
