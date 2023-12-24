package com.simba68.authorizationserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simba68.authorizationserver.entity.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}