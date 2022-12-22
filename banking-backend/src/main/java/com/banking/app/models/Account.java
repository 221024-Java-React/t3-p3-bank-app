package com.banking.app.models;


import java.util.UUID;

import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "account_number")
  private UUID accountId;

  @Enumerated(EnumType.STRING)
  @Column(name = "account_type")
  private AccountType type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_Id")
  private User user;

  private Double balance;

  public Account(String type, User user, Double balance) {
    String utype = type.toUpperCase();
    this.type = AccountType.valueOf(utype);
    this.user = user;
    this.balance = balance;
  }
}
