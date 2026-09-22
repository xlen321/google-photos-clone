package com.project.photos.repos;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.photos.models.User;

public interface UserRepo extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String username);
  
}
