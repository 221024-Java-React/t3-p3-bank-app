package com.banking.app.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.CreditCardApp;
import com.banking.app.models.CreditCardAppStatus;

import com.banking.app.repositories.CreditCardAppRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardAppService {
	private CreditCardAppRepository aRepo;
	
	public CreditCardApp createCreditCardApp(CreditCardApp c) {
		return aRepo.save(c);
	}
	
	public List<CreditCardApp> getCreditCardAppByStatus(CreditCardAppStatus s){
		return aRepo.getCreditCardAppsByStatus(s);
	}
	
	public CreditCardApp getCreditCardAppByApplicationID(int id) {
		return aRepo.getCreditCardAppByApplicationId(id);
	}
	
	public CreditCardApp updateCreditCardApp(int id, CreditCardAppStatus s) {
		CreditCardApp a=aRepo.getCreditCardAppByApplicationId(id);
		a.setStatus(s);
		return aRepo.save(a);
	}
}
