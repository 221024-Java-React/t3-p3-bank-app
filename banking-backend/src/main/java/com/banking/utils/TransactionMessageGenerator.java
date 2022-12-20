package com.banking.utils;

public class TransactionMessageGenerator {
	
	public static String generateMessage(String type, Double amount) {
		String utype = type.toUpperCase();
		String message = "Unidentified Transaction.";
		final String type1 = "WIDTHDRAW";
		final String type2 = "DEPOSIT";
		final String type3 = "TRANSFER";
		
		switch(utype) {
			case type1:
				message = "A widthdrawal of $" + amount + " was made.";
				break;
			case type2:
				message = "A deposit of $" + amount + " was made.";
				break;
			case type3:
				message = "A transfer of $" + amount + " was made.";
				break;
		}
		return message;
	}
}
