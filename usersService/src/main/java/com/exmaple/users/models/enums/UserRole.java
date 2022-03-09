package com.exmaple.users.models.enums;

public enum UserRole {
    USER(1,"user"),
    ADMIN(2, "admin");

    UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int id;
    public String name;
}
