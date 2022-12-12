package com.backend.repository;

import com.backend.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredRepository extends JpaRepository<Long, AppUser> {
}
