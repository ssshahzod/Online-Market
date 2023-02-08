package com.backend.appuser;

import static com.backend.appuser.AppUserRole.*;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority, Serializable {

    private String role = USER.name();

    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

}
