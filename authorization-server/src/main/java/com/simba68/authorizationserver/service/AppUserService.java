package com.simba68.authorizationserver.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.simba68.authorizationserver.dto.CreateAppUser;
import com.simba68.authorizationserver.dto.MessageDto;
import com.simba68.authorizationserver.entity.AppUser;
import com.simba68.authorizationserver.entity.Role;
import com.simba68.authorizationserver.enums.RoleName;
import com.simba68.authorizationserver.repository.AppUserRepo;
import com.simba68.authorizationserver.repository.RoleRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo repo;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(CreateAppUser dto) {
        AppUser appUser = AppUser.builder().username(dto.username()).password(passwordEncoder.encode(dto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        dto.roles().forEach(r -> {
            Role role = repo.findByRole(RoleName.valueOf(r)).orElseThrow(() -> new RuntimeException("role not found"));
            roles.add(role);
        });
        appUser.setRoles(roles);
        appUserRepo.save(appUser);
        return new MessageDto("user" + appUser.getUsername() + " saved!");
    }
}
