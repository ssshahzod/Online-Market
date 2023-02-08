package com.backend.repository;

import com.backend.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query(value = "SELECT id FROM users WHERE email=?1", nativeQuery = true)
    public Long getIdByEmail(String email);

    public AppUser getAppUserByEmail(String email);

}
