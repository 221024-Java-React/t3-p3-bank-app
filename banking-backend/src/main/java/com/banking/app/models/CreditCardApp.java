package com.banking.app.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Credit_Card_Application")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardApp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_Id")
	private Integer applicationId;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CreditCardAppStatus status;
	
	@OneToOne(mappedBy = "appl")
	private CreditCard card;

}
