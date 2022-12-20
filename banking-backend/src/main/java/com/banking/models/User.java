package com.banking.models;

import java.util.List;

import com.banking.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private String userId;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_type")
  private UserType type;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true)
  private String email;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private String address;
  private String phoneNumber;

  @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Account> accounts;

  // Register Constructor
  // Basic Register constructor
  public User(String firstName, String lastName, String email, String address, String phoneNumber, String password) {
    this.userId = IdGenerator.generateId(firstName, lastName);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.password = password;
  }
}
