package com.banking.app.Controllers;

import java.util.LinkedHashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private UserService uServ;

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

  @RequestMapping("/register")
  public User register(@RequestBody LinkedHashMap<String, String> body) {
    String firstName = body.get("firstName");
    String lastName = body.get("lastName");
    String email = body.get("email");
    String address = body.get("address");
    String phoneNumber = body.get("phoneNumber");
    String password = generatePassword();
  }

  @RequestMapping("/login")
  public User login(@RequestBody LinkedHashMap<String, String> body) {
    String firstName = body.get("firstName");
    String password = body.get("password");
  }

}
