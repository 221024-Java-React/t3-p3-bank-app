package com.banking.app.utils;

import com.banking.app.exceptions.CannotUpdateUserException;
import com.banking.app.models.User;
import com.banking.app.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;

public class MessageSender {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    private static final String ACCOUNT_SID = System.getenv("TwilioSID");
    private static final String AUTH_TOKEN = System.getenv("TwilioAuthToken");
    private static UserRepository uRepo;

    public static void SendMessage(User u) {
    Integer randLogId = (int) ((Math.random()*(999999 - 111111))+111111);
    	if(u.getAuthToken().equals(null)) {
    		u.setAuthToken(randLogId);
    		try {
    			@SuppressWarnings("unused")
    			User updatedUser = uRepo.save(u);
    		}catch(Exception e) {
    			throw new CannotUpdateUserException();
    		}
    		
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+1" + u.getPhoneNumber()),
                    new com.twilio.type.PhoneNumber(System.getenv("TwilioNumber")),
                    "Verification Number: " + randLogId)
                .create();

            System.out.println(message.getSid());
    		
    	}
    }
    	
    public static void SendFirstPassword(User u) {
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
