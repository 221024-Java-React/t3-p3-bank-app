package com.banking.app.controllers;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.Account;
import com.banking.app.models.CreditCard;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;
import com.banking.app.models.User;
import com.banking.app.services.AccountService;
import com.banking.app.services.CreditCardService;
import com.banking.app.services.TransactionDataService;
import com.banking.app.services.UserService;
import com.banking.app.utils.TransactionMessageGenerator;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("credit-card")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardController {

  private CreditCardService ccServ;
  private UserService uServ;
  private TransactionDataService tdServ;
  private AccountService aServ;

  @PostMapping("/user")
  public CreditCard getCreditCardByUser(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    User u = uServ.getUserByEmail(email);

    return ccServ.getCreditCardByUser(u);
  }

  @PostMapping("/")
  public CreditCard getCardById(@RequestBody LinkedHashMap<String, Long> body) {
    Long cardId = body.get("cardId");
    return ccServ.getCreditCardByCardId(cardId);
  }

  @PostMapping("/purchase")
  public ResponseEntity<String> payWithCard(@RequestBody LinkedHashMap<String, String> body) {
    Long id = Long.parseLong(body.get("cardId"));
    Double amount = Double.parseDouble(body.get("amount"));
	String message = TransactionMessageGenerator.generateMessage(TransactionType.PURCHASE, amount);
	LocalDate date = LocalDate.now();
	
	CreditCard card = ccServ.getCreditCardByCardId(id);
	
	TransactionData t = new TransactionData();
	t.setType(TransactionType.PURCHASE);
	t.setAmount(amount);
	t.setMessage(message);
	t.setDate(date);
	t.setCard(card);
	tdServ.createTransaction(t);
    
	return new ResponseEntity<>("Puchase made.", HttpStatus.OK);
  }

  @PostMapping("/pay")
  public ResponseEntity<String> payOffCard(@RequestBody LinkedHashMap<String, String> body) {
	Long id = Long.parseLong(body.get("cardId"));
	UUID accountId = UUID.fromString(body.get("accountId"));
	Double amount = Double.parseDouble(body.get("amount")); 
	String cardMessage = TransactionMessageGenerator.generateMessage(TransactionType.PAY, amount);
	LocalDate date = LocalDate.now();
	
	CreditCard card = ccServ.getCreditCardByCardId(id);
	
	TransactionData tCard = new TransactionData();
	tCard.setType(TransactionType.PAY);
	tCard.setAmount(amount);
	tCard.setMessage(cardMessage);
	tCard.setDate(date);
	tCard.setCard(card);
	tdServ.createTransaction(tCard);
	
	Account a = aServ.getAccountByAccountId(accountId);
	String accountMessage = TransactionMessageGenerator.generateMessage(TransactionType.WITHDRAW, amount);
	TransactionData tAccount = new TransactionData();
	tAccount.setType(TransactionType.WITHDRAW);
	tAccount.setAmount(amount);
	tAccount.setMessage(accountMessage);
	tAccount.setDate(date);
	tAccount.setAccount(a);
	tdServ.createTransaction(tAccount);
	ccServ.payCreditCardBalance(id, amount, accountId);
	
	return new ResponseEntity<>("Credit payment made successfully.", HttpStatus.OK);
  }
}
