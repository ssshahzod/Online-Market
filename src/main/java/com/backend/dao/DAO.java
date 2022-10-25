package com.backend.dao;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;

public interface DAO <T extends DTO>{

    public T getByEmailOrNull(String email);

    public void insertOrUpdate(AppUserDTO dto);

    public void delete(T dto);

}
