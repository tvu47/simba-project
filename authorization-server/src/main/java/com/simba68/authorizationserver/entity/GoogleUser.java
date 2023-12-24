package com.simba68.authorizationserver.entity;

import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GoogleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String givenName;
    private String familyName;
    private String pictureUrl;

    public static GoogleUser fromOauth2User(OAuth2User oAuth2User) {
        GoogleUser googleUser = GoogleUser.builder()
                .email(oAuth2User.getName())
                .name(oAuth2User.getAttributes().get("name").toString())
                .givenName(oAuth2User.getAttributes().get("given_name").toString())
                .familyName(oAuth2User.getAttributes().get("family_name").toString())
                .pictureUrl(oAuth2User.getAttributes().get("picture").toString())
                .build();
        return googleUser;
    }

    @Override
    public String toString() {
        return "GoogleUser{" +
                "id=" + id +
                ", email=" + email + '\'' +
                ", name=" + name + '\'' +
                ", givenName=" + givenName + '\'' +
                ", familyName=" + familyName + '\'' +
                ", pictureUrl=" + pictureUrl + '\'' +
                "}";
    }
}
