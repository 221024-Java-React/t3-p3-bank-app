package com.banking.app.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.TransactionData;
import com.banking.app.services.TransactionDataService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("transactions")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {
	
	private TransactionDataService tdServ;
	
	@GetMapping("/")
	public List<TransactionData> getTransactionsByAccountId(UUID accountId) {
		return tdServ.getTransactionsByAccountId(accountId);
	}
	
	@PostMapping("/transaction/create")
	public TransactionData addTransactionToAccount(TransactionData td) {
		return tdServ.createTransaction(td);
	}
	
	@DeleteMapping("/transaction/delete")
	public TransactionData deleteTransactionInAccount(Integer transactionId) {
		return tdServ.deleteTransaction(transactionId);
	}
	 
}
;