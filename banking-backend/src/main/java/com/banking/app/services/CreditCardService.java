package com.banking.app.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.User;
import com.banking.app.models.CreditCard;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;
import com.banking.app.repositories.CreditCardRepository;
import com.banking.app.repositories.TransactionRepository;
import com.banking.app.utils.TransactionMessageGenerator;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardService {
	private CreditCardRepository cRepo;
	private TransactionRepository tRepo;
	
	
	public CreditCard createCreditCard(CreditCard c) {
		Long randLogId = (long) ((Math.random()*(9999999999999999l - 1111111111111111l))+ 1111111111111111l);
		c.setCardId(randLogId);
		return cRepo.save(c);
	}
	
	public CreditCard getCreditCardByUser(User u) {
	    return cRepo.getCreditCardByUser(u);
	}
	
	public CreditCard getCreditCardByAccountId(Long id) {
	    return cRepo.getCreditCardByCardId(id);
	}
	
	public boolean addToCreditCardBalance(Long id, double amount) {
		CreditCard card = cRepo.getCreditCardByCardId(id);
		LocalDate time = LocalDate.now();
		if((card.getBalance()+amount)>card.getCreditLimit()) {
			card.setBalance(card.getBalance()+amount);
			cRepo.save(card);
			TransactionData t = new TransactionData();
		    t.setCard(card);
		    t.setAmount(amount);
		    t.setType(TransactionType.PURCHASE);
		    t.setDate(time);
		    t.setMessage(TransactionMessageGenerator.generateMessage(TransactionType.PURCHASE, amount));
		    tRepo.save(t);
			return true;
		}
		else {
			return false;
		}
	}
	
	public double payCreditCardBalance(Long id, double amount) {
		CreditCard card = cRepo.getCreditCardByCardId(id);
		card.setBalance(card.getBalance()-amount);
		cRepo.save(card);
		LocalDate time = LocalDate.now();
		TransactionData t = new TransactionData();
	    t.setCard(card);
	    t.setAmount(amount);
	    t.setType(TransactionType.PAY);
	    t.setDate(time);
	    t.setMessage(TransactionMessageGenerator.generateMessage(TransactionType.PAY, amount));
	    tRepo.save(t);
		return card.getBalance();
	}
}
