package com.deskify.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deskify.error.RoleNotFoundException;
import com.deskify.model.Permission;
import com.deskify.model.Role;
import com.deskify.repository.PermissionRepository;
import com.deskify.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PermissionRepository permissionRepo;

    public Role assignPermissionsToRole(String roleName, Set<String> permissionNames) {
    
        // Find the role by name or throw an exception if it doesn't exist.
        Role role = roleRepo.findByName(roleName).orElseThrow(() -> new RoleNotFoundException(roleName));

        // Find the permissions by name or throw an exception if they don't exist.  Note that this assumes all
        Set<Permission> permissions = permissionRepo.findByNameIn(permissionNames);

        role.setPermissions(permissions);

        return roleRepo.save(role);
    }
}