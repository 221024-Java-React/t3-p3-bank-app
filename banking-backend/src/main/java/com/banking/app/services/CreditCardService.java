package com.banking.app.services;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.User;
import com.banking.app.repositories.UserRepository;
import com.banking.app.models.CreditCard;
import com.banking.app.repositories.CreditCardRepository;
import com.banking.app.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardService {
	private UserRepository uRepo;
	private CreditCardRepository cRepo;
	
	CreditCard createCreditCard(CreditCard c) {
		Long randLogId = (long) ((Math.random()*(9999999999999999l - 1111111111111111l))+ 1111111111111111l);
		c.setCardId(randLogId);
		return cRepo.save(c);
	}
	
	CreditCard getCreditCardByUser(User u) {
	    return cRepo.getCreditCardByUser(u);
	}
	
	CreditCard getCreditCardByAccountId(UUID id) {
	    return cRepo.getCreditCardByCardId(id);
	}
	
	boolean addToCreditCardBalance(UUID id, double amount) {
		CreditCard card = cRepo.getCreditCardByCardId(id);
		if((card.getBalance()+amount)>card.getCreditLimit()) {
			card.setBalance(card.getBalance()+amount);
			cRepo.save(card);
			return true;
		}
		else {
			return false;
		}
	}
	
	double payCreditCardBalance(UUID id, double amount) {
		CreditCard card = cRepo.getCreditCardByCardId(id);
		card.setBalance(card.getBalance()-amount);
		cRepo.save(card);
		return card.getBalance();
	}
}
