package com.banking.models;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="transaction_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
	
	private TransactionType type;
	private Account account;
	private String message;
	private Double amount;
	private LocalDateTime datetime;
}
