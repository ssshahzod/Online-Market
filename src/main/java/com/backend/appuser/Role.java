package com.backend.appuser;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

    @Id
    private long id;
    private String role;

    @OneToMany
    private Set<AppUser> appUsers;

    public String getAuthority() {
        return role;
    }
}
