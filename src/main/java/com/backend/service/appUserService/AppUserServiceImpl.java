package com.backend.service.appUserService;

import com.backend.appuser.AppUser;
import com.backend.appuser.AppUserRole;
import com.backend.dao.AppUserRepository;
import com.backend.dto.AppUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public boolean save(AppUserDTO appUserDTO){
        if(!Objects.equals(appUserDTO.getPassword(), appUserDTO.getMatchingPassword())){
            throw new RuntimeException("Password is not equal");
        }

        AppUser appUser = AppUser.builder()
                .firstName(appUserDTO.getUsername())
                .password(passwordEncoder.encode(appUserDTO.getPassword()))
                .email(appUserDTO.getEmail())
                .appUserRole(AppUserRole.USER)
                .build();
        appUserRepository.save(appUser);
        logger.info("Write user " + appUser.getUsername() + " to database, Id: " + appUser.getId());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        AppUser appUser = appUserRepository.findFirstByName(userName);
        if(appUser == null){
            throw new UsernameNotFoundException("User with name " + userName + " not found!");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(appUser.getRole()));
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                roles
        );
    }
}
