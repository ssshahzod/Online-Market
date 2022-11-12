package com.backend.dao;

import com.backend.dto.DTO;

public interface DAO <T extends DTO>{

    public void insert(T dto);

    public void update(T newDto);

    public void delete(Long id);

}
