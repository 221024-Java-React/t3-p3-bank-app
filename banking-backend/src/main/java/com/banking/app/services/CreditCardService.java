package com.banking.app.services;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.User;
import com.banking.app.models.Account;
import com.banking.app.models.CreditCard;
import com.banking.app.repositories.AccountRepository;
import com.banking.app.repositories.CreditCardRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardService {
	private CreditCardRepository ccRepo;
	private AccountRepository aRepo;
	public CreditCard createCreditCard(CreditCard c) {
		Long randLongId = (long) ((Math.random()*(9999999999999999l - 1111111111111111l))+ 1111111111111111l);
		c.setCardId(randLongId);
		return ccRepo.save(c);
	}
	
	public CreditCard getCreditCardByUser(User u) {
	    try {
	      return ccRepo.getCreditCardByUser(u);
	    } catch (Exception e) {
	      throw new NoSuchElementException();
	    }
	}
	
	public CreditCard getCreditCardByCardId(Long cardId) {
	    try {
	      return ccRepo.getCreditCardByCardId(cardId);
	    } catch (Exception e) {
	      throw new NoSuchElementException();
	    }
	}
	
	public void addToCreditCardBalance(Long id, double amount) {
		try {
			CreditCard card = ccRepo.getCreditCardByCardId(id);
				card.setBalance(card.getBalance()+amount);
				ccRepo.save(card);
	    } catch (Exception e) {
	      throw new NoSuchElementException();
	    }
	}
	
	public void payCreditCardBalance(Long cardId, double amountPaid, UUID accountId) {
		try {
			CreditCard card = ccRepo.getCreditCardByCardId(cardId);
			Account a = aRepo.getAccountByAccountId(accountId);
			Double current = a.getBalance();
			
			a.setBalance(current-amountPaid);
			aRepo.save(a);
			
			card.setBalance(card.getBalance()-amountPaid);
			ccRepo.save(card);
			
	    } catch (NoSuchElementException e) {
	      throw new NoSuchElementException();
	    }
	}
}
