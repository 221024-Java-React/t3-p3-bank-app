package com.banking.Controllers;

import java.util.LinkedHashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.models.User;
import com.banking.services.UserService;

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

  @PostMapping("/register")
  public User register(@RequestBody LinkedHashMap<String, String> body) {
    User newUser = new User();
    newUser.setFirstName(body.get("firstName"));
    String firstName = body.get("firstName");
    String lastName = body.get("lastName");
    String email = body.get("email");
    String address = body.get("address");
    String phoneNumber = body.get("phoneNumber");
    String password = generatePassword();

    return uServ.registerUser(newUser);
  }

  @PostMapping("/login")
  public User login(@RequestBody LinkedHashMap<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");

    return uServ.loginUser(email, password);
  }

  @PutMapping("/update")
  public User update(@RequestBody User user) {
    return uServ.updateUser(user);
  }

}
