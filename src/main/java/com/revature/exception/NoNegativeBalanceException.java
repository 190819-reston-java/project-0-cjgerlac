package com.revature.exception;

import org.apache.log4j.Logger;

public class NoNegativeBalanceException extends RuntimeException {

	private static Logger logger = Logger.getLogger(NoNegativeBalanceException.class);

	public NoNegativeBalanceException() {
		this("You are attempting to withdraw more money than what is available in your account!");
		logger.trace("User attempted to withdraw more money than funds available.");
	}

	public NoNegativeBalanceException(String errorMessage) {
		super(errorMessage);
	}
}
