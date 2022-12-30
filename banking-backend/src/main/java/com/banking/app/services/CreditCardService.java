package com.banking.app.services;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.User;
import com.banking.app.models.CreditCard;
import com.banking.app.repositories.CreditCardRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardService {
	private CreditCardRepository cRepo;
	public CreditCard createCreditCard(CreditCard c) {
		Long randLogId = (long) ((Math.random()*(9999999999999999l - 1111111111111111l))+ 1111111111111111l);
		c.setCardId(randLogId);
		return cRepo.save(c);
	}
	
	public CreditCard getCreditCardByUser(User u) {
		CreditCard c = null;

	    try {
	      return cRepo.getCreditCardByUser(u);
	    } catch (NoSuchElementException e) {
	      return c;
	    }
	}
	
	public CreditCard getCreditCardByAccountId(Long id) {
		CreditCard c = null;

	    try {
	      return cRepo.getCreditCardByCardId(id);
	    } catch (NoSuchElementException e) {
	      return c;
	    }
	}
	
	public boolean addToCreditCardBalance(Long id, double amount) {
		try {
			CreditCard card = cRepo.getCreditCardByCardId(id);
			if((card.getBalance()+amount)>card.getCreditLimit()) {
				card.setBalance(card.getBalance()+amount);
				cRepo.save(card);
				return true;
			}
			else {
				return false;
			}
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
	
	public double payCreditCardBalance(Long id, double amount) {
		try {
			CreditCard card = cRepo.getCreditCardByCardId(id);
			card.setBalance(card.getBalance()-amount);
			cRepo.save(card);
			return card.getBalance();
	    } catch (NoSuchElementException e) {
	      return 0.0;
	    }
	}
}
