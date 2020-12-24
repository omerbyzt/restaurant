package com.ba.entity;

public interface BaseDomain {

    Long getId();
    void setId(Long id);

    boolean isDeleted();
    void setDeleted(boolean deleted);
}
