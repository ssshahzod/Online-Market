package com.backend.appuser;

import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    @Id
    private Long id;
    private String name;

    @Transient
    @ManyToOne()
    Set<AppUser> appUsers;

    @Override
    public String getAuthority() {
        return AppUserRoles.;
    }

    Long getId() {
        return id;
    }

    void setId(final Long id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    Set<AppUser> getAppUsers() {
        return appUsers;
    }

    void setAppUsers(final Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
