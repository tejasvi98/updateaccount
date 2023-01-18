package com.dojo.account.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidUserExceptionTest {

	@Test
	void invalidUserException() {
		InvalidUserException userNotFoundException = new InvalidUserException("invalid user exception");
		assertEquals("invalid user exception", userNotFoundException.getMessage());
	}

}
