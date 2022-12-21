package com.banking.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.repositories.AccountRepository;
import com.banking.repositories.UserRepository;
import com.banking.repositories.TransactionRepository;
//import com.banking.exceptions.ClaimAlreadyProcessedException;
//import com.banking.exceptions.NotAnEmployeeException;

import com.banking.models.Account;
import com.banking.models.AccountType;
import com.banking.models.User;
import com.banking.models.TransactionData;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AccountService {
	private AccountRepository cRepo;
	private UserRepository uRepo;
	private TransactionRepository tRepo;
	
	/*public Account createAccount(String email, String accountId, User user, AccountType type, Integer balance) {
		User u = uRepo.getByEmail(email).get();
		Account acc = new Account(accountId, u, type, balance);
		return cRepo.save(acc);
	}*/
	public Account createAccount(Account a) {
		//User u = uRepo.getByEmail(email).get();
		//Account acc = new Account(accountId, u, type, balance);
		return cRepo.save(a);
	}
	
	public List<Account> getAccounts(int accountId) {
		User u = uRepo.findById(accountId).get();
		
		return cRepo.getAccountsBySubmitter(u);
	}
	
	public Account getAccountById(String id) {
		return cRepo.getAccountByAccountId(id);
	}

	public List<Account> getAccountsByType(AccountType s) {
		
		return cRepo.getAccountsByType(s);
	}
	
	public TransactionData transferBetweenAccounts(String accountIdFrom, String accountIdTo, Double amount) {
		Account from = cRepo.getAccountByAccountId(accountIdFrom);
		Account to = cRepo.getAccountByAccountId(accountIdTo);
		
		double fromA = from.getBalance()-amount;
		from.setBalance(fromA);
		double toA = to.getBalance()+amount;
		to.setBalance(toA);
		cRepo.save(from);
		cRepo.save(to);
		LocalDate time = LocalDate.now();
		
		TransactionData tFrom = new TransactionData();
		tFrom.setAccountId(from.getAccountId());
		tFrom.setAmount(amount);
		//tFrom.setType(TransactionType.Withdraw);
		tFrom.setDate(time);
		
		TransactionData tTo = new TransactionData();
		tTo.setAccountId(to.getAccountId());
		tTo.setAmount(amount);
		//tFrom.setType(TransactionType.DEPOSIT);
		tFrom.setDate(time);
		
		tRepo.save(tTo);
		tRepo.save(tFrom);
		
		return tFrom;
	}
}