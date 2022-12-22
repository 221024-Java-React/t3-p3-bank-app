package com.banking.app.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private UserService uServ;
  private AccountService aServ;

  private static char randomChar() {
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random r = new Random();
    char c = alphabet.charAt(r.nextInt(alphabet.length()));
    return c;
  }

  private static String generatePassword() {
    String password = "";
    for (int i = 0; i < 8; i++) {
      password = password + randomChar();
    }
    return password;
  }

  @PostMapping("/register")
  public User register(@RequestBody LinkedHashMap<String, String> body) {
    String firstName = body.get("firstName");
    String lastName = body.get("lastName");
    String email = body.get("email");
    String address = body.get("address");
    String phoneNumber = body.get("phoneNumber");
    String password = "";
    String accountType = body.get("accountType");
    Double balance = 0.0;

    User newUser = new User(firstName, lastName, email, address, phoneNumber, password);
    uServ.registerUser(newUser);
    User registeredUser = uServ.getUserByEmail(email);

    if (accountType == "both") {
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

    return uServ.loginUser(email, password);
  }

  @PutMapping("/update")
  public User updateUser(@RequestBody LinkedHashMap<String, String> body) {
    String firstName = body.get("firstName");
    String lastName = body.get("lastName");
    String email = body.get("email");

    return uServ.updateUser(firstName, lastName, email);
  }

  @PutMapping("/update_password")
  public User updatePassword(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");

    return uServ.updatePassword(email, password);
  }

  @PostMapping("/user")
  public User userByEmail(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");

    return uServ.getUserByEmail(email);
  }

  // @PostMapping("/members")
  // public List<User> allUsers() {
  // return uServ.getAllUsers();
  // }

}
