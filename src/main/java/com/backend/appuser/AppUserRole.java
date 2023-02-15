package com.backend.appuser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum AppUserRole {
    USER(new HashSet<Permission>() {{add(Permission.USER_READ); add(Permission.USER_WRITE);}}),
    SELLER(new HashSet<Permission>() {{add(Permission.SELLER_READ); add(Permission.SELLER_ADD);}}),
    ADMIN(new HashSet<Permission>() {{add(Permission.USER_READ); add(Permission.USER_WRITE);
                                    add(Permission.SELLER_READ); add(Permission.SELLER_ADD);}});

    private final Set<Permission> permissions;

    AppUserRole(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        final Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
