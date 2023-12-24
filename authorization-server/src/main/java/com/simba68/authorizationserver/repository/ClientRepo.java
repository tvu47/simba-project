package com.simba68.authorizationserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simba68.authorizationserver.entity.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findByClientId(String clientId);
}
