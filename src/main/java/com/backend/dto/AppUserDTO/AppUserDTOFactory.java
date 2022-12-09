package com.backend.dto.AppUserDTO;


import com.backend.appuser.AppUserRoles;

@Deprecated
public class AppUserDTOFactory {

    static public AppUserDTO defaultAppUserDTO(){
        AppUserDTO appUserDTO = new AppUserDTO();
        return appUserDTO;
    }

    static public AppUserDTO newAppUser(String firstName, String secondName, String email,
                                        String password, AppUserRoles appUserRole, boolean isArchived){

        return null;
    }
}
