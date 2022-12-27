package com.banking.app.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.app.models.User;
import com.banking.app.models.Account;
import com.banking.app.models.CreditCard;
import com.banking.app.models.CreditCardApp;
import com.banking.app.models.CreditCardStatusType;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;

@Repository
public class CreditCardAppRepository  extends JpaRepository<CreditCardApp, Integer> {
	List<CreditCardApp> getCreditCardAppsByUser(User u);
	List<CreditCardApp> getCreditCardAppsByStatus(StatusType s);
	CreditCardApp getCreditCardAppByApplicationId(int id);
}
