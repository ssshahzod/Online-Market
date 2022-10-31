package com.backend.dto.AppUserDTO;


import com.backend.appuser.AppUser;
import com.backend.appuser.AppUserRole;
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
    private AppUserRole appUserRole = AppUserRole.USER;
    private boolean isArchived = false;
    //TODO: add default values, to be able to create new usersessions for guests

    public AppUserDTO(AppUser appUser){
        this.firstName = appUser.getFirstName();
        this.secondName = appUser.getSecondName();
        this.email = appUser.getEmail();
        this.password = appUser.getPassword();
        this.appUserRole = appUser.getAppUserRole();
        this.isArchived = appUser.isArchive();
    }
}
