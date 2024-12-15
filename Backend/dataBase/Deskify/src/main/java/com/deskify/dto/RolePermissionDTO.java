package com.deskify.dto;

public class RolePermissionDTO {
    
    private RoleDTO role;
    
    private PermissionDTO permission;

    public RolePermissionDTO() {
    }

    public RolePermissionDTO(RoleDTO role, PermissionDTO permission) {
        this.role = role;
        this.permission = permission;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public PermissionDTO getPermission() {
        return permission;
    }

    public void setPermission(PermissionDTO permission) {
        this.permission = permission;
    }

}
