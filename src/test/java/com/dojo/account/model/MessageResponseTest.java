package com.dojo.account.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class MessageResponseTest {

	@Test
	void getterTest() {
		MessageResponse messageResponse = new MessageResponse(new Date(1999,10,10),"success", HttpStatus.ACCEPTED);
		String str = messageResponse.getMessage()+ messageResponse.getStatus()+messageResponse.getTimeStamp();
		assertTrue(str.contains(messageResponse.getMessage()));
	}
	
	@Test
	void setterTest() {
		MessageResponse messageResponse = new MessageResponse();
		messageResponse.setMessage("some");
		messageResponse.setStatus(HttpStatus.ACCEPTED);
		messageResponse.setTimeStamp(new Date(1999,10,10));
		assertEquals("some",messageResponse.getMessage());
	}

}
