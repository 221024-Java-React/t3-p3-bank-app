package com.banking.app.utils;

import com.banking.app.exceptions.CannotUpdateUserException;
import com.banking.app.models.User;
import com.banking.app.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import jakarta.transaction.Transactional;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;




@Configuration
@Transactional
public class MessageSender {

  private static final String ACCOUNT_SID = System.getenv("TWILIOSID");
  private static final String AUTH_TOKEN = System.getenv("TWILIOAUTHTOKEN");
  
  @Autowired
  private UserService uServ;

  public void SendMessage(User u) {
    Integer randLogId = (int) ((Math.random() * (999999 - 111111)) + 111111);
    if (u.getAuthToken() == null) {
      u.setAuthToken(randLogId);
      try {
        @SuppressWarnings("unused")
        User updatedUser = uServ.updateFullUser(u);
      } catch (Exception e) {
        throw new CannotUpdateUserException();
      }

      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      @SuppressWarnings("unused")
      Message message = Message.creator(
          new com.twilio.type.PhoneNumber("+1" + u.getPhoneNumber()),
          new com.twilio.type.PhoneNumber(System.getenv("TWILIONUMBER")),
          "Verification Number: " + randLogId)
          .create();
    }
  }

  public void SendFirstPassword(User u) {
    String tempPass = u.getPassword();
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    @SuppressWarnings("unused")
    Message message = Message.creator(
        new com.twilio.type.PhoneNumber("+1" + u.getPhoneNumber()),
        new com.twilio.type.PhoneNumber(System.getenv("TwilioNumber")),
        "This is a tempory password, please reset after login: " + tempPass)
        .create();
  }

}
