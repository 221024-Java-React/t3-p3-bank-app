package com.banking.app.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.models.Account;
import com.banking.app.models.User;
import com.banking.app.services.AccountService;
import com.banking.app.services.UserService;
import com.banking.app.utils.MessageSender;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private UserService uServ;
  private AccountService aServ;
  private MessageSender mSend;

  @PostMapping("/register")
  public User register(@RequestBody LinkedHashMap<String, String> body) {
    String firstName = body.get("firstName");
    String lastName = body.get("lastName");
    String email = body.get("email");
    String address = body.get("address");
    String phoneNumber = body.get("phoneNumber");
    String accountType = body.get("accountType").toLowerCase();
    Double balance = 0.0;

    User u = new User(firstName, lastName, email, address, phoneNumber);
    uServ.registerUser(u);
    User registeredUser = uServ.getUserByEmail(email);
    mSend.SendFirstPassword(registeredUser);

    if (accountType.equals("both")) {
      Account accountChecking = new Account("checking", registeredUser, balance);
      Account accountSavings = new Account("savings", registeredUser, balance);
      aServ.createAccount(accountChecking);
      aServ.createAccount(accountSavings);
    } else {
      Account account = new Account(accountType, registeredUser, balance);
      aServ.createAccount(account);
    }

    return registeredUser;
  }

  @PostMapping("/login")
  public User login(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");
    User u = uServ.loginUser(email, password);
    if(!u.getFirstLogin()) {
    	mSend.SendMessage(u);
    } 
    return u;
  }
  
  @PutMapping("/reset-password")
  public User resetPassword(@RequestBody LinkedHashMap<String, String> body) {
	  String email = body.get("email");
	  String password = body.get("password");
	  
	  User u = uServ.getUserByEmail(email);
	  u.setPassword(password);
	  u.setFirstLogin(false);
	  u = uServ.updateUser(u);
	  return u;
  }
  
  @PostMapping("/authenticate")
  public User loginAuth(@RequestBody LinkedHashMap<String, String> body) {
    String email = (String) body.get("email");
    Integer authToken = Integer.parseInt(body.get("token"));
    return uServ.authenticateUser(email, authToken);
  }

	/*
	 * @PostMapping("/forgot") public ResponseEntity<String>
	 * forgotPassword(@RequestBody LinkedHashMap<String, String> body) { String
	 * email = body.get("email"); User u = uServ.getUserByEmail(email);
	 * mSend.SendMessage(u); return new ResponseEntity<>("Message Sent",
	 * HttpStatus.OK); }
	 * 
	 * @PutMapping("/forgot_Auth") public ResponseEntity<String>
	 * forgotPassAuth(@RequestBody LinkedHashMap<String, String> body) { String
	 * email = body.get("email"); Integer authToken =
	 * Integer.parseInt(body.get("token")); User u = uServ.getUserByEmail(email);
	 * if(u.getAuthToken().equals(authToken)) { String randPass =
	 * RandomPasswordGenerator.generatePassword(); u.setPassword(randPass);
	 * u.setFirstLogin(true); User updatedUser = uServ.updateFullUser(u);
	 * mSend.SendFirstPassword(updatedUser); uServ.logout(email); return new
	 * ResponseEntity<>("Message Sent", HttpStatus.OK); }else { return new
	 * ResponseEntity<>("Message Sent", HttpStatus.BAD_REQUEST); } }
	 */



  @PutMapping("/logout")
  public ResponseEntity<String> logout(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    uServ.logout(email);
    return new ResponseEntity<>("Logged out Successfully", HttpStatus.OK);
  }

  @PostMapping("/user")
  public User getUserByEmail(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    return uServ.getUserByEmail(email);
  }
}
