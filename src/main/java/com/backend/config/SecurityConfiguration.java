package com.backend.config;

import com.backend.repository.AppUserCredentialsDAO;
import com.backend.service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsService appUserDetailsService;
    private JdbcTemplate jdbcTemplate;

    public SecurityConfiguration(AppUserDetailsService appUserDetailsService, JdbcTemplate jdbcTemplate) {
        this.appUserDetailsService = appUserDetailsService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService myUserDetailsService(){
        return new AppUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .antMatchers("/admin").hasRole("ROLE_ADMIN")
                .antMatchers("/").permitAll()
                .antMatchers("*/products").hasRole("ROLE_SELLER")
                .and()
                .formLogin();
        return http.build();
    }

/*    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new AppUserCredentialsDAO(jdbcTemplate);
        daoAuthenticationProvider.setUserDetailsService(appUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }*/


}
