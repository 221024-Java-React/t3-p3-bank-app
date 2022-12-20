package com.banking.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private String userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String address;
  private String phoneNumber;
  private UserType type;
  private List<Account> accounts;
}
