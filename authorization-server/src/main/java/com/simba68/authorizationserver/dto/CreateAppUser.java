package com.simba68.authorizationserver.dto;

import java.util.List;

public record CreateAppUser(
        String username,
        String password,
        List<String> roles) {

}
