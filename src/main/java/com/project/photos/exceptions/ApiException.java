package com.project.photos.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter 
public abstract class ApiException extends RuntimeException {
  private final HttpStatus status;

  public ApiException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }
}
