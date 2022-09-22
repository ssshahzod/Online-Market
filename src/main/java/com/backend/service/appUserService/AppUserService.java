package com.backend.service.appUserService;

import com.backend.dto.AppUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService { //security
    boolean save(AppUserDTO appUserDTO);

}
