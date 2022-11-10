package com.backend.repository;

import com.backend.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT (email) DO NOTHING;\n"
            + "appUser.getFirstName(), appUser.getSecondName(), appUser.getEmail(), appUser.isArchived(),\n"
            + "appUser.getPassword(), appUser.getAppUserRole().name())")
    public void insert(AppUser appUser);

    @Modifying
    @Query("")
    public void update(AppUser appUser);
}
