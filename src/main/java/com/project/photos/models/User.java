package com.project.photos.models;

import java.util.UUID;

import com.project.photos.audit.AuditDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
  name = "users",
  indexes = {
    @Index(name = "idx_user_email", columnList = "email")
  }
)
@Getter
@Setter
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditDetails {
  @Id 
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false, length = 100)
  private String password;
}
