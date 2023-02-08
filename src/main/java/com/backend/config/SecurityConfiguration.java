package com.backend.config;

import com.backend.repository.AppUserCredentialsDAO;
import com.backend.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


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
                .formLogin()
                .loginPage("/users/login");//if removed actual login is going in /login
        //instead it asks for authentication provider

        return http.build();
    }

/*  @Autowired
    private JdbcTemplate jdbcTemplate;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new AppUserCredentialsDAO(jdbcTemplate);
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }*/


}
