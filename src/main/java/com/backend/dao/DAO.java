package com.backend.dao;

import com.backend.dto.DTO;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DAO <T extends DTO> {

    public void insert(T dto);

    public void update(Long id, T newDto);

    public void delete(Long id);

}
