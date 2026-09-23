package com.project.photos.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.photos.exceptions.ApiException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiResponse<?>> handleApiException(
      ApiException ex,
      HttpServletRequest request) {
    return buildErrorResponse(
        ex.getStatus(),
        ex.getMessage(),
        request.getRequestURI(),
        null);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> handleValidationException(
      MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    Map<String, String> validationErrors = new HashMap<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      validationErrors.put(error.getField(), error.getDefaultMessage());
    }
    return buildErrorResponse(
        HttpStatus.BAD_REQUEST,
        "Validation failed",
        request.getRequestURI(),
        validationErrors);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<?>> handleGenericException(
      Exception ex,
      HttpServletRequest request) {
    return buildErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Something went wrong. Please try again later.",
        request.getRequestURI(),
        null);
  }

  private ResponseEntity<ApiResponse<?>> buildErrorResponse(
      HttpStatus status,
      String message,
      String path,
      Map<String, String> validationErrors) {
    ApiError error = new ApiError(
        status.value(),
        status.getReasonPhrase(),
        message,
        path,
        validationErrors);

    return ResponseEntity.status(status).body(ApiResponse.error(error));
  }
}
