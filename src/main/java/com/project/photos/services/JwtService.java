package com.project.photos.services;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.photos.config.JwtProperties;
import com.project.photos.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

  @Value("${app.jwt.secret}")
  private String JWT_SECRET_KEY;

  private final JwtProperties jwtProperties;

  public String generateAccessToken(User user) {

    Instant now = Instant.now();
    Instant expiry = now.plusMillis(jwtProperties.access_token_expiration());

    return Jwts.builder()
        .subject(user.getId().toString())
        .claim("email", user.getEmail())
        .claim("type", "access")
        .issuedAt(Date.from(now))
        .expiration(Date.from(expiry))
        .signWith(getSecretKey())
        .compact();

  }

  public String generateRefreshToken(User user) {
    Instant now = Instant.now();
    Instant expiry = now.plusMillis(jwtProperties.refresh_token_expiration());

    return Jwts.builder()
        .subject(user.getId().toString())
        .claim("type", "refresh")
        .issuedAt(Date.from(now))
        .expiration(Date.from(expiry))
        .signWith(getSecretKey())
        .compact();
  }

  public UUID getUserIdFromToken(String token) {
    Claims claims = Jwts.parser()
        .verifyWith(getSecretKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();

    return UUID.fromString(claims.getSubject());
  }

  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
  }
}
