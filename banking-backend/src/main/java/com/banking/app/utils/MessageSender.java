package com.banking.app.utils;

import com.banking.app.models.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;

public class MessageSender {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("TwilioSID");
    public static final String AUTH_TOKEN = System.getenv("TwilioAuthToken");

    public static void SendMessage(User u) {
    Integer randLogId = (int) ((Math.random()*(999999 - 111111))+111111);
    	if(u.getAuthToken().equals(null)) {
    		u.setAuthToken(randLogId);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+1" + u.getPhoneNumber()),
                    new com.twilio.type.PhoneNumber(System.getenv("TwilioNumber")),
                    "Verification Number: " + randLogId)
                .create();

            System.out.println(message.getSid());
    		
    	}

    }
}
