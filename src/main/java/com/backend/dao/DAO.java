package com.backend.dao;

import com.backend.dto.AppUserDTO;
import com.backend.dto.DTO;

public interface DAO <T extends DTO>{

    public T getByEmail(String email);

    public void insert(AppUserDTO dto);

    public void update(T dto);

    public void delete(T dto);

}
