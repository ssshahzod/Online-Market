package com.backend.dao;

import com.backend.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findFirstByName(String name);


}
