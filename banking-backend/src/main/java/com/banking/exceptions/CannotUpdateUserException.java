package com.banking.exceptions;

public class CannotUpdateUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CannotUpdateUserException() {
		super("Cannot update user.");
	}

}