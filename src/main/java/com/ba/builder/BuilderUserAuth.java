package com.ba.builder;

public abstract class BuilderUserAuth<T> {
    private String username;

    public abstract T build();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
