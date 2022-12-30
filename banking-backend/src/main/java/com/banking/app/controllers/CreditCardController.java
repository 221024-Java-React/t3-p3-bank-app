package com.banking.app.controllers;

import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.CreditCard;
import com.banking.app.models.User;
import com.banking.app.services.CreditCardService;
import com.banking.app.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("credit-card")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardController {

  private CreditCardService cServ;
  private UserService uServ;

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
    // UUID id = (UUID) body.get("card");
    Long id = (Long) body.get("userId");
    Double amount = (Double) body.get("amount");
    return cServ.addToCreditCardBalance(id, amount);
  }

  @PostMapping("/pay")
  public <T> double payOffCard(@RequestBody LinkedHashMap<String, T> body) {
    // UUID id = (UUID) body.get("card");
    Long id = (Long) body.get("userId");
    Double amount = (Double) body.get("amount");
    return cServ.payCreditCardBalance(id, amount);
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
