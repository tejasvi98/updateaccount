package com.dojo.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dojo.account.client.LoginClient;
import com.dojo.account.exception.ConstraintException;
import com.dojo.account.exception.InvalidUserException;
import com.dojo.account.exception.UserNotFoundException;
import com.dojo.account.model.AuthResponse;
import com.dojo.account.model.CustomerDetailsDTO;
import com.dojo.account.model.SuccessResponse;

@SpringBootTest(classes = { AccountServiceTest.class })
class AccountServiceTest {

	@InjectMocks
	AccountService accountService;

	@Mock
	LoginClient loginClient;

	@Test
	void testUpdateUser() throws InvalidUserException, UserNotFoundException, ConstraintException {
		String token = "token";
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo", "demo1234", "test", "test", "test", "test",
				"test@test.com", "test", 1234567890, LocalDate.of(2020, 1, 8), "testAccount");
		ResponseEntity<AuthResponse> responseEntity = ResponseEntity.ok(new AuthResponse("demo", true));
		ResponseEntity<CustomerDetailsDTO> customerEntity = ResponseEntity.ok(user);
		when(loginClient.verifyToken(token)).thenReturn(responseEntity);
		when(loginClient.findById(token, "demo")).thenReturn(customerEntity);
		when(loginClient.updateDetails(user))
				.thenReturn(new SuccessResponse("success", HttpStatus.ACCEPTED, LocalDate.now()));
		SuccessResponse updateUser = accountService.updateUser("token", user);
		assertEquals("success", updateUser.getMessage());
	}

	@Test
	void testUpdateUserFailure() throws InvalidUserException, UserNotFoundException, ConstraintException {
		String token = "token";
		CustomerDetailsDTO user = new CustomerDetailsDTO("demo", "demo1234", "test", "test", "test", "test",
				"test@test.com", "test", 1234567890, LocalDate.of(2020, 1, 8), "testAccount");
		ResponseEntity<AuthResponse> responseEntity = ResponseEntity.ok(new AuthResponse("demo", false));
		ResponseEntity<CustomerDetailsDTO> customerEntity = ResponseEntity.ok(user);
		when(loginClient.verifyToken(token)).thenReturn(responseEntity);
		when(loginClient.findById(token, "demo")).thenReturn(customerEntity);
		when(loginClient.updateDetails(user))
				.thenReturn(new SuccessResponse("success", HttpStatus.ACCEPTED, LocalDate.now()));
		assertThrows(InvalidUserException.class, () -> accountService.updateUser("token", user));
	}

	@Test
	void testUpdateUserWithEmptyFields() throws InvalidUserException, UserNotFoundException, ConstraintException {
		CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
		String token = "token";
		ResponseEntity<AuthResponse> responseEntity = ResponseEntity.ok(new AuthResponse("demo", false));
		ResponseEntity<CustomerDetailsDTO> customerEntity = ResponseEntity.ok(customerDetailsDTO);
		when(loginClient.verifyToken(token)).thenReturn(responseEntity);
		when(loginClient.findById(token, "demo")).thenReturn(customerEntity);
		when(loginClient.updateDetails(customerDetailsDTO))
				.thenReturn(new SuccessResponse("success", HttpStatus.ACCEPTED, LocalDate.now()));
		assertThrows(ConstraintException.class, () -> accountService.updateUser("token", customerDetailsDTO));
	}

}
