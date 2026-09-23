package com.project.photos.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

  public ConflictException(HttpStatus status, String message) {
    super(HttpStatus.CONFLICT, message);
  }

}
