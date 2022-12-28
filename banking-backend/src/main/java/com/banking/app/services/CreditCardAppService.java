package com.banking.app.services;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.User;
import com.banking.app.repositories.UserRepository;
import com.banking.app.models.CreditCard;
import com.banking.app.models.CreditCardApp;
import com.banking.app.models.CreditCardAppStatus;

import com.banking.app.repositories.CreditCardRepository;
import com.banking.app.repositories.CreditCardAppRepository;
import com.banking.app.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardAppService {
	private UserRepository uRepo;
	private CreditCardRepository cRepo;
	private CreditCardAppRepository aRepo;
	
	CreditCardApp createCreditCardApp(CreditCardApp c) {
		return aRepo.save(c);
	}
	
	
	List<CreditCardApp> getCreditCardAppByStatus(CreditCardAppStatus s){
		return aRepo.getCreditCardAppsByStatus(s);
	}
	
	CreditCardApp getCreditCardAppByApplicationID(int id) {
		return aRepo.getCreditCardAppByApplicationId(id);
	}
	
	CreditCardApp updateCreditCardApp(int id, CreditCardAppStatus s) {
		CreditCardApp a=aRepo.getCreditCardAppByApplicationId(id);
		a.setStatus(s);
		return aRepo.save(a);
	}
}
