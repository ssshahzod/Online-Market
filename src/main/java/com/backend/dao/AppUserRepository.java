package com.backend.dao;

import com.backend.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT t FROM users t WHERE t.name= ?1")
    AppUser findFirstByName(String name);


}
