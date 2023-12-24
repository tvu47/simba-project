package com.simba68.authorizationserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simba68.authorizationserver.entity.Role;
import com.simba68.authorizationserver.enums.RoleName;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(RoleName roleName);
}
