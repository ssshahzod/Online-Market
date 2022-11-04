package com.backend.dao;

import com.backend.dto.DTO;

public interface DAO <T extends DTO>{

    public T getByValueOrNull(String value);

    public void insert(T dto);

    public void update(T dto);

    public void delete(T dto);

}
