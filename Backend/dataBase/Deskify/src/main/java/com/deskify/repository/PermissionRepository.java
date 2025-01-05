package com.deskify.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deskify.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Set<Permission> findByNameIn(Set<String> permissionNames);

}
