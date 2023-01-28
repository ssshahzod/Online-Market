package com.backend.appuser;

import java.util.HashSet;
import java.util.Set;

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
}
