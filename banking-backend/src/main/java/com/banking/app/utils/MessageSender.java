package com.banking.app.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessageSender {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = System.getenv("TwilioSID");
    public static final String AUTH_TOKEN = System.getenv("TwilioAuthToken");

    public static void Send(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+14159352345"),
                new com.twilio.type.PhoneNumber(System.getenv("TwilioNumber")),
                "Verification Number: ")
            .create();

        System.out.println(message.getSid());
    }
}
