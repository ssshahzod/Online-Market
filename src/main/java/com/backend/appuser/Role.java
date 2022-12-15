package com.backend.appuser;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private long id;
    private String role;

    @OneToMany
    private Set<AppUser> appUsers;

    @Override
    public String getAuthority() {
        return role;
    }
}
