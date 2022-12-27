package com.banking.app.models;


//import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
//import lombok.Data;
import lombok.experimental.SuperBuilder;





@Entity
@Table(name = "Credit_Card")
@AllArgsConstructor
@SuperBuilder
public class CreditCard extends Account{
	
	public CreditCard() {
		
		super.setType(AccountType.CREDIT);
	}
	@Column(name = "Limit")
	private Double creditLimit;
	
	@Column(name = "balanced_Owed")
	private Double balance;
}
