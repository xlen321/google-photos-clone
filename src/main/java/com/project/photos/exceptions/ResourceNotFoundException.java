package com.project.photos.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

  public ResourceNotFoundException(HttpStatus status, String message) {
    super(HttpStatus.NOT_FOUND, message);

  }

}
