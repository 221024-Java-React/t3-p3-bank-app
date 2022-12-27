package com.banking.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.app.models.User;
import com.banking.app.models.Account;
import com.banking.app.models.CreditCard;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;

@Repository
public class CreditCardRepository  extends JpaRepository<CreditCard, Integer> {
	CreditCard getCreditCardByUser(User u);
	CreditCard getCreditCardByAccountId(UUID id);
	//List<CreditCard> getCreditCards();
}
