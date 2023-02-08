package com.backend.dto.AppUserDTO;


import com.backend.appuser.AppUser;
import com.backend.appuser.AppUserRole;
import com.backend.appuser.Role;
import com.backend.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO implements DTO {
    //private AppUser appUser;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String role;
    private boolean isArchived = false;

    public AppUserDTO(AppUser appUser){
        this.firstName = appUser.getFirstName();
        this.secondName = appUser.getSecondName();
        this.email = appUser.getEmail();
        this.role = appUser.getRole();
        this.password = appUser.getPassword();
    }
}
