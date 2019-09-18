package com.revature.exception;

import org.apache.log4j.Logger;

public class InvalidLogin extends RuntimeException {

	private static Logger logger = Logger.getLogger(InvalidLogin.class);

	public InvalidLogin(String message) {
		super(message);
		logger.error("Invalid login attempt.");
	}

	public InvalidLogin() {
		this("Invalid login account name.");
	}
}
