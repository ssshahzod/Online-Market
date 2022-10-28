package com.backend.dao;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;

public interface DAO <T extends DTO>{

    public T getByValueOrNull(String value);

    public void insertOrUpdate(T dto);

    public void delete(T dto);

}
