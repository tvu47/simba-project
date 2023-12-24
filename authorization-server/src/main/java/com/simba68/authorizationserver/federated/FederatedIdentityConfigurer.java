package com.simba68.authorizationserver.federated;

import java.util.function.Consumer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FederatedIdentityConfigurer
        extends AbstractHttpConfigurer<FederatedIdentityConfigurer, HttpSecurity> {
    private Consumer<OAuth2User> oauth2UserHandler;
    private Consumer<OidcUser> oidcUser;

    public FederatedIdentityConfigurer soauth2UserHandler(Consumer<OAuth2User> oath2) {
        Assert.notNull(oath2, "oauth2UserHandle can not be empty!");
        this.oauth2UserHandler = oath2;
        return this;
    }

    public FederatedIdentityConfigurer oidcUser(Consumer<OidcUser> oidc) {
        Assert.notNull(oidc, "oidc can not be empty!");
        this.oidcUser = oidc;
        return this;
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        FederatedIdentityAuthenticationSuccessHandler aSuccessHandler = new FederatedIdentityAuthenticationSuccessHandler();
        if (this.oauth2UserHandler != null) {
            aSuccessHandler.setOAuth2UserHandler(oauth2UserHandler);
        }
        if (this.oidcUser != null) {
            aSuccessHandler.setOidcUserHandler(oidcUser);
        }
    }
}