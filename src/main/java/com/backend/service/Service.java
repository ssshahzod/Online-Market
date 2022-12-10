package com.backend.service;


public interface Service <T>{
    public void create(T value);

    public T get(String value);

    public Long getId(String value);
}
