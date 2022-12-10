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
    @OneToMany(mappedBy = role)
    Set<AppUser> appUsers;

    public Role(){}

    public Role (Long id){
        this.id = id;
    }

    public Role (Long id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
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
