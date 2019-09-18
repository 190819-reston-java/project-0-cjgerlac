package com.revature.exception;

import org.apache.log4j.Logger;

public class InvalidPassword extends RuntimeException {

	private static Logger logger = Logger.getLogger(InvalidPassword.class);

	public InvalidPassword(String message) {
		super(message);
		logger.warn("Invalid password attempt.");
	}

	public InvalidPassword() {
		this("Invalid password! Try again.");
	}

}
