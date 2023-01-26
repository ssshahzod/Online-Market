package com.backend.appuser;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private long id;
    private String role;

    @OneToMany
    private Set<AppUser> appUsers;

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
