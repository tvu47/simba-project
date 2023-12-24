package com.simba68.authorizationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simba68.authorizationserver.entity.Role;
import com.simba68.authorizationserver.enums.RoleName;
import com.simba68.authorizationserver.repository.RoleRepo;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class AuthorizationServerApplication implements CommandLineRunner {

	@Autowired
	RoleRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = Role.builder().role(RoleName.ROLE_ADMIN).build();
		Role roleUser = Role.builder().role(RoleName.ROLE_USER).build();
		repo.save(adminRole);
		repo.save(roleUser);
	}

}
