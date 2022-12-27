package com.banking.app.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	
	public List<TransactionData> getTransactionsByAccountId(UUID accountId) {
		return tdServ.getTransactionsByAccountId(accountId);
	}
}
