package com.banking.app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.User;
import com.banking.app.models.CreditCard;
import com.banking.app.models.CreditCardApp;
import com.banking.app.models.CreditCardAppStatus;
import com.banking.app.services.CreditCardService;
import com.banking.app.services.UserService;

import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

import com.banking.app.services.CreditCardAppService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("credit-card-app")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardAppController {

  private CreditCardAppService aServ;
  private CreditCardService cServ;
  private UserService uServ;
  /*
  @PostMapping("/create")
  public CreditCardApp createApplication(@RequestBody CreditCardApp a) {
    return aServ.createCreditCardApp(a);
  }
  */
  @PostMapping("/filter/{status}")
  public List<CreditCardApp> getCreditCardAppsByStatus(@PathVariable("status") Integer status) {
    CreditCardAppStatus s;

    if (status.equals(0)) {
      s = CreditCardAppStatus.APPROVED;
    } else {
      s = CreditCardAppStatus.DENIED;
    }

    return aServ.getCreditCardAppByStatus(s);
  }

  @PostMapping("/id/{id}")
  public CreditCardApp getCreditCardAppByUserId(@PathVariable("id") int id) {
    return aServ.getCreditCardAppByApplicationID(id);
  }

  @PostMapping("/create")
  public CreditCardApp createCreditCardApp(@RequestBody LinkedHashMap<String, String> body) {
    System.out.println(body.get("age"));
    Integer age = Integer.parseInt(body.get("age"));
    
    System.out.println(body.get("userId"));
    UUID userId = UUID.fromString(body.get("userId"));
    User u = uServ.getUserById(userId);
    String email = u.getEmail();
    CreditCard currentCard = cServ.getCreditCardByUser(uServ.getUserById(userId));
    
    System.out.println(body.get("creditScore"));
    Integer creditScore = Integer.parseInt(body.get("creditScore"));
    
    System.out.println(body.get("monthlyIncome"));
    Double monthlyIncome = Double.parseDouble(body.get("monthlyIncome"));
    
    System.out.println(body.get("netWorth"));
    Double netWorth = Double.parseDouble(body.get("netWorth"));
    
    System.out.println(body.get("estDebt"));
    Double estDebt = Double.parseDouble(body.get("estDebt"));
    
    System.out.println(body);
    CreditCardAppStatus s;
    CreditCardApp cardApp = new CreditCardApp();
    CreditCard newCard = new CreditCard();
    if(age<=15 || creditScore<=300 || !currentCard.equals(null)) {
    	s = CreditCardAppStatus.DENIED;
    	cardApp.setStatus(s);
    	cardApp.setCard(cServ.getCreditCardByUser(uServ.getUserById(userId)));
    	cardApp.setAge(age);
    	cardApp.setCreditScore(creditScore);
    	cardApp.setMonthlyIncome(monthlyIncome);
    	cardApp.setNetWorth(netWorth);
    	cardApp.setEstDebt(estDebt);
    	cardApp.setApprovedLimit(0.0);
    	cardApp.setApplicant(email);
    }
    else {
    	s = CreditCardAppStatus.APPROVED;
    	cardApp.setStatus(s);
    	
    	cardApp.setAge(age);
    	cardApp.setCreditScore(creditScore);
    	cardApp.setMonthlyIncome(monthlyIncome);
    	cardApp.setNetWorth(netWorth);
    	cardApp.setEstDebt(estDebt);
    	cardApp.setApprovedLimit(age, creditScore, monthlyIncome, estDebt);
    	cardApp.setApplicant(email);
    	
    	Double limit = cardApp.getApprovedLimit();
    	newCard.setUser(u);
    	newCard.setCreditLimit(limit);
    	newCard.setBalance(0.0);
    	newCard.setAppl(cardApp);
    	
    	cardApp.setCard(cServ.getCreditCardByUser(uServ.getUserById(userId)));
    	
    	cServ.createCreditCard(newCard);
    }
    
    return aServ.createCreditCardApp(cardApp);
  }

}
