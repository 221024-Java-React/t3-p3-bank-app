package com.banking.Controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.models.Account;
import com.banking.models.TransactionData;
import com.banking.services.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("accounts")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class AccountController {
	
	private AccountService aServ;
	
	@PostMapping("/create")
	public Account createAccount(@RequestBody Account a) {
		return aServ.createAccount(a);
	}
	
	@PostMapping("/transfer")
	public <T> TransactionData transferFunds(@RequestBody LinkedHashMap<String, T> body) {
		String accountIdFrom = (String) body.get("accountIdFrom");
		String accountIdTo = (String) body.get("accountIdTo");
		Double amount = (Double) body.get("amount");
		
		return aServ.transferBetweenAccounts(accountIdFrom, accountIdTo, amount);
	}

}