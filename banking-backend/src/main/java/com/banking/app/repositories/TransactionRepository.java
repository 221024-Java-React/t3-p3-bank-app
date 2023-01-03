package com.banking.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.app.models.Account;
import com.banking.app.models.CreditCard;
//import com.banking.app.models.Account;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;

public interface TransactionRepository extends JpaRepository<TransactionData, Integer> {
	
  List<TransactionData> getTransactionsByAccount(Account a);
  List<TransactionData> getTransactionsByCard(CreditCard c);
  List<TransactionData> getTransactionsByType(TransactionType t);
  TransactionData getTransactionDataByTransactionId(int transactionid);
}

