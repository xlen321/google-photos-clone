package com.project.photos.dto;

public record AuthResponse(
    String accessToken,
    String refreshToken,
    UserResponse user) {

}
