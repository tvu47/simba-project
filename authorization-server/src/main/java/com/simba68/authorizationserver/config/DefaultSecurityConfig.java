package com.simba68.authorizationserver.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.simba68.authorizationserver.federated.FederatedIdentityAuthenticationSuccessHandler;
import com.simba68.authorizationserver.federated.FederatedIdentityConfigurer;
import com.simba68.authorizationserver.federated.UserRepositoryOAuth2UserHandler;
import com.simba68.authorizationserver.repository.GoogleUserRepository;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class DefaultSecurityConfig {

        private final GoogleUserRepository googleUserRepository;

        @Bean
        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
                        throws Exception {
                http.cors(Customizer.withDefaults());
                FederatedIdentityConfigurer federatedIdentityConfigurer = new FederatedIdentityConfigurer()
                                .soauth2UserHandler(new UserRepositoryOAuth2UserHandler(googleUserRepository));
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/auth/**", "/client/**", "/assets/**", "/login")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login"))
                                .oauth2Login(oauth2Login -> oauth2Login
                                                .loginPage("/login")
                                                .successHandler(authenticationSuccessHandler()))
                                .apply(federatedIdentityConfigurer);
                http.logout(logout -> logout.logoutSuccessUrl("http://localhost:4200/logout"));
                http.csrf(csrf -> csrf.ignoringRequestMatchers("/auth/**", "/client/**", "/login/**", "/assets/**"));
                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                CorsConfiguration cors = new CorsConfiguration();
                cors.addAllowedHeader("*");
                cors.addAllowedMethod("*");
                cors.setAllowCredentials(true);
                cors.setAllowedOrigins(List.of("http://localhost:4200"));
                source.registerCorsConfiguration("/**", cors);
                return source;
        }

        private AuthenticationSuccessHandler authenticationSuccessHandler() {
                return new FederatedIdentityAuthenticationSuccessHandler();
        }

        @Bean
        public SessionRegistry sessionRegistry() {
                return new SessionRegistryImpl();
        }

        @Bean
        public HttpSessionEventPublisher httpSessionEventPublisher() {
                return new HttpSessionEventPublisher();
        }
}
