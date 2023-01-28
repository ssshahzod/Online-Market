package com.backend.appuser;

public enum Permission {
    USER_READ("user:read"), //watch products
    USER_WRITE("user:write"), //write profile, fill bucket
    SELLER_READ("seller:read"), //watch products?
    SELLER_ADD("seller:add"); //add products to sell

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
