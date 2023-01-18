package com.dojo.account.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {RestExceptionHandlerTest.class})
public class RestExceptionHandlerTest {
	
	@Test
	void test_constraintException() {
		String message = "test";
		ConstraintException constraintException = new ConstraintException(message);
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();	
		restExceptionHandler.handleConstraintException(constraintException);
	}
	
	@Test
	void test_userNotFoundException() {
		String message = "test";
		UserNotFoundException constraintException = new UserNotFoundException(message);
		RestExceptionHandler restExceptionHandler = new RestExceptionHandler();	
		restExceptionHandler.handleUserNotFound(constraintException);
	}
	
	@Test
    void invalidUserException() {
        String message = "test";
        InvalidUserException invalidUserException = new InvalidUserException(message);
        RestExceptionHandler restExceptionHandler = new RestExceptionHandler(); 
        restExceptionHandler.handleInvalidUserException(invalidUserException);
    }
	
	
}
