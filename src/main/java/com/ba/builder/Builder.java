package com.ba.builder;

public abstract class Builder<T> {

    private Long id;

    public abstract T build();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
