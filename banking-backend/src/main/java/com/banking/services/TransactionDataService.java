package com.banking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.models.Account;
//import com.banking.models.AccountType;
import com.banking.models.TransactionData;
import com.banking.models.User;
import com.banking.repositories.AccountRepository;
//import com.banking.repositories.UserRepository;
import com.banking.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class TransactionDataService {
	private AccountRepository aRepo;
	//private UserRepository uRepo;
	private TransactionRepository tRepo;
	
	public TransactionData createTransaction(TransactionData t) {
		return tRepo.save(t);
	}
	
	public List<TransactionData> getTransactionsByAccountId(String accountId) {
		Account a = aRepo.getAccountByAccountId(accountId);
		return tRepo.getTransactionDatasByAccount(a);
	}
	
	public List<TransactionData> getTransactionDatasByUser(User u) {
		return tRepo.getTransactionDatasByUser(u);
	}
	
	public TransactionData getTransactionById(int id) {
		return tRepo.getTransactionDataByTransactionDataId(id);
	}
}
