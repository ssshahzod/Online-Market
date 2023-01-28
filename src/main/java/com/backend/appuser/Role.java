package com.backend.appuser;

import static com.backend.appuser.AppUserRole.*;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public class Role implements GrantedAuthority, Serializable {

    private String role = USER.name();


    public void setRole(String role){
        this.role = role;
    }

    public Role(String roleName){
        this.role = roleName;
    }
    @Override
    public String getAuthority() {
        return role;
    }

}
