package com.banking.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.models.Account;
import com.banking.app.models.TransactionData;
import com.banking.app.models.User;
import com.banking.app.repositories.AccountRepository;
import com.banking.app.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionDataService {
	private AccountRepository aRepo;
	private TransactionRepository tRepo;
	
	public TransactionData createTransaction(TransactionData t) {
		return tRepo.save(t);
	}
	
	public List<TransactionData> getTransactionsByAccountId(UUID accountId) {
		Account a = aRepo.getAccountByAccountId(accountId);
		return tRepo.getTransactionDataByAccount(a);
	}
	
	public List<TransactionData> getTransactionDataByUser(User u) {
		return tRepo.getTransactionDataByUser(u);

	}
	
	public TransactionData getTransactionById(int id) {
		return tRepo.getTransactionDataByTransactionDataId(id);
	}
}
