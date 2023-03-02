package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {


    private AppUserService appUserService;

    @Autowired
    public void setAppUserService(AppUserService appUserService){
        this.appUserService = appUserService;
    }
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        AppUserDTO appUserDTO = appUserService.get(email);
        if(appUserDTO == null){
            throw new UsernameNotFoundException(email);
        }
        return new AppUser(appUserDTO);
    }
}
