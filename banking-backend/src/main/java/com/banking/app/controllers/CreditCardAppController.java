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
    Integer age = Integer.parseInt(body.get("age"));
    UUID userId = UUID.fromString(body.get("userId"));
    User u = uServ.getUserById(userId);
    String email = u.getEmail();

    Integer creditScore = Integer.parseInt(body.get("creditScore"));

    Double monthlyIncome = Double.parseDouble(body.get("monthlyIncome"));

    Double netWorth = Double.parseDouble(body.get("netWorth"));

    Double estDebt = Double.parseDouble(body.get("totalMiscPayments")) + Double.parseDouble(body.get("rent"))
        + Double.parseDouble(body.get("carPayment"));

    
      CreditCardApp app = new CreditCardApp();
      app.setStatus(CreditCardAppStatus.APPROVED);
      app.setAge(age);
      app.setCreditScore(creditScore);
      app.setMonthlyIncome(monthlyIncome);
      app.setNetWorth(netWorth);
      app.setEstimatedDebt(estDebt);
      app.setApprovedLimit(age, creditScore, monthlyIncome, estDebt);
      app.setApplicant(email);
      
      CreditCard newCard = new CreditCard();
      newCard.setUser(u);
      newCard.setCreditLimit(app.getApprovedLimit());
      newCard.setBalance(0.0);
      newCard.setApp(app);
      cServ.createCreditCard(newCard);
      
      app.setCard(cServ.getCreditCardByUser(uServ.getUserById(userId)));
      

    return aServ.createCreditCardApp(app);
  }

}
