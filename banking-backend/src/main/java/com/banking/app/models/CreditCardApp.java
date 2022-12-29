package com.banking.app.models;


import org.springframework.beans.factory.annotation.Autowired;

import com.banking.app.utils.LimitCalculator;

//import com.banking.app.utils.LimitCalculator;

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
	
	@Autowired
	LimitCalculator limCal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_Id")
	private Integer applicationId;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CreditCardAppStatus status;
	
	@OneToOne(mappedBy = "appl")
	private CreditCard card;
	
	@Column(name = "applicant_age")
	private Integer age;

	@Column(name = "credit_score")
	private Integer creditScore;
	
	@Column(name = "monthly_income")
	private Double monthlyIncome;
	
	@Column(name = "net_worth")
	private Double netWorth;
	
	@Column(name = "Estimated_debt")
	private Double estDebt;
	
	@Column(name = "approved_limit")
	private Double approvedLimit;
	
	public void setApprovedLimit(Integer age, Integer score, Double income, Double debt) {
		double dti = limCal.calcMaxDti(age, score);
		Double lim = limCal.calcCreditLimit(income, score, dti);
		this.approvedLimit = lim;
	}
}
