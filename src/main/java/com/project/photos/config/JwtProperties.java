package com.project.photos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(
    String secret,
    long access_token_expiration,
    long refresh_token_expiration) {

}
