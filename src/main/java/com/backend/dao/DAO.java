package com.backend.dao;

import com.backend.dto.DTO;

public interface DAO {

    public DTO getByEmail(String email);

    public void insert(DTO dto);

    public void update(DTO dto);

    public void delete(DTO dto);

}
