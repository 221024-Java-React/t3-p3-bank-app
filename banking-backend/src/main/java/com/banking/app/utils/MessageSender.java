package com.banking.app.utils;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.banking.app.exceptions.CannotUpdateUserException;
import com.banking.app.models.User;
import com.banking.app.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.transaction.Transactional;

@Transactional
@Configuration
public class MessageSender {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    private static final String ACCOUNT_SID = System.getenv("TwilioSID");
    private static final String AUTH_TOKEN = System.getenv("TwilioAuthToken");
    
    @Autowired
    private UserRepository uRepo;
    

    
    public void SendMessage(User u) {
    Integer randLogId = (int) ((Math.random()*(999999 - 111111))+111111);
    Optional<User>	tempU = uRepo.getByEmail(u.getEmail());
    if(tempU.isPresent()) {
    	tempU.get().setAuthToken(randLogId);	
//    	u.setAuthToken(randLogId);
//    		try {
//    			@SuppressWarnings("unused")
//    			User updatedUser = uRepo.save(u);
//    		}catch(Exception e) {
//    			throw new CannotUpdateUserException();
//    		}
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+1" + u.getPhoneNumber()),
                    new com.twilio.type.PhoneNumber(System.getenv("TwilioNumber")),
                    "Verification Number: " + randLogId)
                .create();

            System.out.println(message.getSid());
    	}
    }
    
    	
    public void SendFirstPassword(User u) {
    	String firstPass = u.getPassword();
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+1" + u.getPhoneNumber()),
                new com.twilio.type.PhoneNumber(System.getenv("TwilioNumber")),
                "Verification Number: " + firstPass)
            .create();
        System.out.println(message.getSid());
    }
  
}
