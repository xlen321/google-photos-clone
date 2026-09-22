package com.project.photos.services;

import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.photos.models.User;
import com.project.photos.repos.UserRepo;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService implements UserDetailsService {
  private final UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo
        .findByEmail(username)
        .orElseThrow(
            () -> new BadCredentialsException("User with provided email: " + username + " not found"));
  }

  public User getUserById(UUID id) {
    return userRepo
        .findById(id)
        .orElseThrow(
            () -> new BadCredentialsException("User with provided id: " + id + " not found"));

  }

}
