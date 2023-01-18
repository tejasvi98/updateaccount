package com.dojo.account.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class SuccessResponseTest {

	@Test
	void setter() {
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setMessage("success");
		successResponse.setStatus(HttpStatus.OK);
		successResponse.setTimestamp(LocalDate.now());
		assertNotNull(successResponse);
	}
	@Test
	void getter() {
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setMessage("success");
		successResponse.setStatus(HttpStatus.OK);
		successResponse.setTimestamp(LocalDate.now());
		String str = successResponse.getMessage()+successResponse.getStatus()+successResponse.getTimestamp();
		assertTrue(str.contains(successResponse.getMessage()));
	}

}
