package com.backend.service;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;

public interface Service <T extends DTO>{
    public void create(T DTO);
}
