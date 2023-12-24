package com.simba68.authorizationserver.federated;

// tag::imports[]
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// end::imports[]
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.simba68.authorizationserver.entity.GoogleUser;
import com.simba68.authorizationserver.repository.GoogleUserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {
    @Autowired
    private GoogleUserRepository googleUserRepository;

    @Override
    public void accept(OAuth2User user) {
        // Capture user in a local data store on first authentication
        log.info("Google User not found {}", user.getAttributes().get("given_name"));
        if (!this.googleUserRepository.findByEmail(user.getName()).isPresent()) {
            GoogleUser googleUser = GoogleUser.fromOauth2User(user);
            System.out.println(googleUser.toString());
            this.googleUserRepository.save(googleUser);
        } else {
            log.info("Google User not found {}", user.getAttributes().get("given_name"));
        }
    }
}
// end::class[]