package com.deskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deskify.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
