package com.project.photos.advice;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
  @Builder.Default
  private Instant timestamp = Instant.now();
  private T data;
  private ApiError error;

  public static <T> ApiResponse<T> success(T data) {
    return ApiResponse.<T>builder()
        .data(data)
        .build();
  }

  public static <T> ApiResponse<T> error(ApiError error) {
    return ApiResponse.<T>builder()
        .error(error)
        .build();
  }
}
