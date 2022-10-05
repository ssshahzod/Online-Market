package com.backend.dto;


import com.backend.appuser.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO implements DTO{
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private AppUserRole appUserRole;
    private boolean isArchived;

}
