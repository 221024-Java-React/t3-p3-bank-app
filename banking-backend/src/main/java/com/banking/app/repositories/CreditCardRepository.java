package com.banking.app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.app.models.User;
import com.banking.app.models.CreditCard;

@Repository
public interface CreditCardRepository  extends JpaRepository<CreditCard, Integer> {
	CreditCard getCreditCardByUser(User u);
	CreditCard getCreditCardByCardId(UUID id);
	//List<CreditCard> getCreditCards();
}
