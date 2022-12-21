package com.banking.app.models;

import java.time.LocalDate;

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
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_Id")
  private Integer transactionId;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_type")
  private TransactionType type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_number")
  private String accountId;

  @Column(name = "details")
  private String message;

  private Double amount;

  @Column(name = "date_time")
  private LocalDate date;

  // public TransactionData(String type, String accountId, Double amount) {}
}
