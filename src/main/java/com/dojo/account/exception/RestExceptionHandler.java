package com.dojo.account.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dojo.account.model.MessageResponse;

import lombok.extern.slf4j.Slf4j;

//global exception handler
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {	
	/**Checks header of the request that the user enters.
	 * handles expires JWT token
	 * @param ExpiredJwtExceptionException
	 * @return ResponseEntity<MessageResponse>
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MessageResponse> handleUserNotFound(Exception ex) {

		log.error("User Not Found");
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}
	   @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(InvalidUserException.class)
	    public ResponseEntity<MessageResponse> handleInvalidUserException(Exception ex) {

	        log.error("Invalid user exception");
	        return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	    }
	   @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(ConstraintException.class)
	    public ResponseEntity<MessageResponse> handleConstraintException(ConstraintException ex) {

	        log.error("failed constraint");
	        return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	    }
}
