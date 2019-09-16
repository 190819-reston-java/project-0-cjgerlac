package com.revature.testing;

import org.junit.Assert;
import org.junit.Test;

import com.revature.service.Services;

public class WithdrawalAttempts {

	public static Services servicio = new Services();
	
	@Test
	public void WithdrawAttemptOne() {
	servicio.setU(servicio.getUserDAO().getUser("hello"));
	Assert.assertTrue(servicio.verifyUserId("Hello!") == true);
	}
}
