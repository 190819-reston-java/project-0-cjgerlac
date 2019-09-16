package com.revature.testing;

import org.junit.Assert;
import org.junit.Test;

import com.revature.service.Services;

public class PasswordTesting {

public static Services servicio = new Services();
	
	@Test
	public void WithdrawAttemptOne() {
	servicio.setU(servicio.getUserDAO().getUser("hello"));
	Assert.assertTrue(servicio.verifyUserPassword("") == true);
	}
	
	@Test
	public void WithdrawAttemptTwo() {
	servicio.setU(servicio.getUserDAO().getUser("hello"));
	Assert.assertTrue(servicio.verifyUserPassword("wOrLd") == true);
	}
	
	@Test
	public void WithdrawAttemptThree() {
	servicio.setU(servicio.getUserDAO().getUser("hello"));
	Assert.assertTrue(servicio.verifyUserPassword("world") == true);
	}
}
