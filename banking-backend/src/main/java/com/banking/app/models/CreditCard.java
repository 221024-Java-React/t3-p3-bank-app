package com.banking.app.models;


import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Credit_Card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "account_number")
	private UUID cardId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_Id")
	private User user;
	
	@Column(name = "credit_limit")
	private Double creditLimit;
	
	private Double balance;
	
	@OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
	@JsonIgnore
	List<TransactionData> transactions;
	
	@OneToOne
	@JoinColumn(name = "application_Id")
	private CreditCardApp appl;
}
