package com.project.photos.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

  public BadRequestException(HttpStatus status, String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
  
}
