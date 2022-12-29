package com.banking.app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.CreditCardApp;
import com.banking.app.models.CreditCardAppStatus;
import com.banking.app.services.CreditCardAppService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("credit-card-app")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardAppController {

  private CreditCardAppService aServ;

  @PostMapping("/create")
  public CreditCardApp createApplication(@RequestBody CreditCardApp a) {
    return aServ.createCreditCardApp(a);
  }

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
  public CreditCardApp getAccountsByUserId(@PathVariable("id")int id) {
    return aServ.getCreditCardAppByApplicationID(id);
  }
  
  @PostMapping("/update")
  public CreditCardApp getAccountsByUserId(@RequestBody LinkedHashMap<String, Integer> body) {
    System.out.println(body.get("id") instanceof Integer);
    System.out.println(body.get("status") instanceof Integer);
    System.out.println(body);
    CreditCardAppStatus s;
	if (body.get("id").equals(0)) {
		s = CreditCardAppStatus.APPROVED;
	} else {
		s = CreditCardAppStatus.DENIED;
	}
    return aServ.updateCreditCardApp(body.get("id"),s);
  }

}
