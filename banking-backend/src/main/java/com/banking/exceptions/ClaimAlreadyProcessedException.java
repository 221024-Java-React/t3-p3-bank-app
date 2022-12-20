package com.banking.exceptions;

public class ClaimAlreadyProcessedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClaimAlreadyProcessedException() {
		super("Claim already processed.");
	}

}