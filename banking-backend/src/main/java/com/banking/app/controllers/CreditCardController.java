package com.banking.app.controllers;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

  private CreditCardService cServ;
  private UserService uServ;
  private TransactionDataService tdServ;
  private AccountService aServ;

  @PostMapping("/create")
  public CreditCard createCard(@RequestBody CreditCard a) {
    return cServ.createCreditCard(a);
  }

  @PostMapping("/user")
  public CreditCard getCardByUser(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    User u = uServ.getUserByEmail(email);

    return cServ.getCreditCardByUser(u);
  }

  @PostMapping("/cardId")
  public CreditCard getCardById(@RequestBody LinkedHashMap<String, Long> body) {
    Long cardId = body.get("userId");
    return cServ.getCreditCardByAccountId(cardId);
  }

  @PostMapping("/buy")
  public <T> boolean payWithCard(@RequestBody LinkedHashMap<String, T> body) {
    Long id = (Long) body.get("cardId");
    Double amount = (Double) body.get("amount");
    String type = (String) body.get("type");
	String message = TransactionMessageGenerator.generateMessage(TransactionType.valueOf(type), amount);
	LocalDate date = LocalDate.now();
	
	CreditCard card = cServ.getCreditCardByAccountId(id);
	
	TransactionData t = new TransactionData();
	t.setType(TransactionType.valueOf(type));
	t.setAmount(amount);
	t.setMessage(message);
	t.setDate(date);
	t.setCard(card);
	tdServ.createTransaction(t);
    
    return cServ.addToCreditCardBalance(id, amount);
  }

  @PostMapping("/pay")
  public <T> double payOffCard(@RequestBody LinkedHashMap<String, T> body) {
	Long id = (Long) body.get("cardId");
	UUID accountId = (UUID) body.get("accountId");
	Double amount = (Double) body.get("amount");
	String type = (String) body.get("type");
	String message = TransactionMessageGenerator.generateMessage(TransactionType.valueOf(type), amount);
	LocalDate date = LocalDate.now();
	
	CreditCard card = cServ.getCreditCardByAccountId(id);
	
	TransactionData tCard = new TransactionData();
	tCard.setType(TransactionType.valueOf(type));
	tCard.setAmount(amount);
	tCard.setMessage(message);
	tCard.setDate(date);
	tCard.setCard(card);
	tdServ.createTransaction(tCard);
	
	Account a = aServ.getAccountById(accountId);
	String messageAccount = TransactionMessageGenerator.generateMessage(TransactionType.WIDTHDRAW, amount);
	TransactionData tAccount = new TransactionData();
	tAccount.setType(TransactionType.WIDTHDRAW);
	tAccount.setAmount(amount);
	tAccount.setMessage(messageAccount);
	tAccount.setDate(date);
	tAccount.setAccount(a);
	tdServ.createTransaction(tAccount);
	
	return cServ.payCreditCardBalance(id, amount, accountId);
  }
  /*
   * @PostMapping("/account")
   * public List<Account> getAccountsByUserId(@RequestBody LinkedHashMap<String,
   * String> body) {
   * System.out.println(body.get("userId") instanceof String);
   * System.out.println(body);
   * UUID userID = UUID.fromString(body.get("userId"));
   * return aServ.getAccountsByUserId(userID);
   * }
   */

  /*
   * public CreditCard getCreditCardByUser(User u) {
   * return cRepo.getCreditCardByUser(u);
   * }
   * 
   * public CreditCard getCreditCardByAccountId(UUID id) {
   * return cRepo.getCreditCardByCardId(id);
   * }
   * 
   * public boolean addToCreditCardBalance(UUID id, double amount) {
   * CreditCard card = cRepo.getCreditCardByCardId(id);
   * if((card.getBalance()+amount)>card.getCreditLimit()) {
   * card.setBalance(card.getBalance()+amount);
   * cRepo.save(card);
   * return true;
   * }
   * else {
   * return false;
   * }
   * }
   * 
   * public double payCreditCardBalance(UUID id, double amount) {
   * CreditCard card = cRepo.getCreditCardByCardId(id);
   * card.setBalance(card.getBalance()-amount);
   * cRepo.save(card);
   * return card.getBalance();
   * }
   */
}
