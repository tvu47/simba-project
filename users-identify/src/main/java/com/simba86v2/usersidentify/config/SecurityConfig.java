package com.simba86v2.usersidentify.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // @Value("${auth0.audience}")
        // private String audience;

        // @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
        // private String issuer;

        // @Bean
        // JwtDecoder jwtDecoder() {

        // NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
        // JwtDecoders.fromOidcIssuerLocation(issuer);

        // OAuth2TokenValidator<Jwt> audienceValidator = new
        // AudienceValidator(audience);
        // OAuth2TokenValidator<Jwt> withIssuer =
        // JwtValidators.createDefaultWithIssuer(issuer);
        // OAuth2TokenValidator<Jwt> withAudience = new
        // DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        // jwtDecoder.setJwtValidator(withAudience);

        // return jwtDecoder;
        // }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors(withDefaults())
                                .csrf(csrf -> csrf
                                                .disable())
                                .authorizeHttpRequests(requests -> requests
                                                .requestMatchers("/auth/**", "/api/**")
                                                .permitAll()
                                                .requestMatchers("/api/private")
                                                .authenticated()
                                                .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_admin"))
                                .oauth2ResourceServer(c -> c.jwt(withDefaults()));
                return http.build();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(
                                Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Origin",
                                                "Content-Type", "X-Auth-Token"));
                configuration.setMaxAge(3600L);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}
