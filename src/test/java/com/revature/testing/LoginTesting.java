package com.revature.testing;

import org.junit.Assert;
import org.junit.Test;

import com.revature.service.Services;

public class LoginTesting {

	static Services userTest = new Services();
	
	@Test
	public void InvalidId() {
		Assert.assertTrue(userTest.verifyUserId("Hello!") == true);
	}
	
	@Test
	public void InvalidIdTwo() {
		Assert.assertTrue(userTest.verifyUserId("asdf") == true);
	}
	
	@Test
	public void ValidId() {
		Assert.assertTrue(userTest.verifyUserId("hello") == true);
	}
}
