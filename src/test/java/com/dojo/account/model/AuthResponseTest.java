package com.dojo.account.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthResponseTest {

	@Test
	void setter() {
		AuthResponse authResponse = new AuthResponse();
		authResponse.setUsername("test");
		authResponse.setValid(true);
		assertEquals("test", authResponse.getUsername());
	}

	@Test
	void ToString() {
		AuthResponse authResponse = new AuthResponse();
		authResponse.setUsername("test");
		authResponse.setValid(true);
		assertTrue(authResponse.toString().contains(authResponse.getUsername()));
	}

}
