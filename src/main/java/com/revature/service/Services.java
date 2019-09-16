package com.revature.service;

import java.text.NumberFormat;
import org.apache.log4j.Logger;
import com.revature.exception.InvalidLogin;
import com.revature.exception.InvalidPassword;
import com.revature.model.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserImplPJDBC;

public class Services {

	private static Logger logger = Logger.getLogger(Services.class);

	private User u = null;
	private UserDAO userDAO = new UserImplPJDBC();
	private NumberFormat toCurrency = NumberFormat.getCurrencyInstance();

	public boolean verifyUserId(String userInput) {
		try {
			if (!(getUserDAO().getUser(userInput) == null))
				return true;
			else
				throw new InvalidLogin();
		} catch (InvalidLogin e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean verifyUserPassword(String userInput) {
		String pw = getU().getPassword();
		try {
			if (userInput.equals(pw))
				return true;
			else
				throw new InvalidPassword();
		} catch (InvalidPassword e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void displayBalance() {
		double balance = getU().getBalance();
		System.out.println(toCurrency.format(balance));
	}

	public void checkValidDepositAmount(String depositInput) {
		try {
			Double deposit = Double.valueOf(depositInput);
			if (deposit < 0) {
				logger.warn("No negative deposits!");
			} else {
				if (getUserDAO().updateUser(getU(), deposit) == true) {
					System.out.println("Successful deposit!");
					u.setBalance(u.getBalance() + deposit);
				}
			}
		} catch (NumberFormatException e) {
			logger.warn("Input was not a number!");
		}
	}

	public void withdrawalAmount(String withdrawalAmount) {
		try {
			Double withdrawal = Double.valueOf(withdrawalAmount);
			if (withdrawal > 0) {
				logger.warn("Put in a negative withdrawal amount!");
			} else {
				if (getUserDAO().updateUser(getU(), withdrawal) == true) {
					System.out.println("Successful withdrawal!");
					u.setBalance(u.getBalance() + withdrawal);
				}
			}
		} catch (NumberFormatException e) {
			logger.warn("Input was not a number!");
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

}
