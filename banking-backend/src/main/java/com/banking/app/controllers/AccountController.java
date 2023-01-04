package com.banking.app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.Account;
import com.banking.app.models.TransactionData;
import com.banking.app.services.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("accounts")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

  private AccountService aServ;

  @PostMapping("/create")
  public Account createAccount(@RequestBody Account a) {
    return aServ.createAccount(a);
  }

  @PostMapping("/transfer")
  public TransactionData transferFunds(@RequestBody LinkedHashMap<String, String> body) {
    UUID accountIdFrom = UUID.fromString(body.get("accountIdFrom"));
    UUID accountIdTo = UUID.fromString(body.get("accountIdTo"));
    Double amount = Double.parseDouble(body.get("amount"));
    return aServ.transferBetweenAccounts(accountIdFrom, accountIdTo, amount);
  }

  @PostMapping("/account")
  public List<Account> getAccountsByUserId(@RequestBody LinkedHashMap<String, String> body) {
    return aServ.getAccountsByUserId(UUID.fromString(body.get("userId")));
  }
  
  @PostMapping("/deposit")
  public ResponseEntity<String> depositToAccount(@RequestBody LinkedHashMap<String, String>body) {
    UUID accountId = UUID.fromString(body.get("accountId"));
    Double amount = Double.parseDouble(body.get("amount"));
    aServ.depositToAccount(accountId, amount);
	return new ResponseEntity<>("$"+amount+" was deposited", HttpStatus.OK);
  }
  
  @PostMapping("/withdraw")
  public ResponseEntity<String> withdrawFromAccount(@RequestBody LinkedHashMap<String, String>body) {
	UUID accountId = UUID.fromString(body.get("accountId"));
	Double amount = Double.parseDouble(body.get("amount"));
	aServ.withdrawFromAccount(accountId, amount);
	return new ResponseEntity<>("$"+amount+" was withdrawn", HttpStatus.OK);
  }

}
