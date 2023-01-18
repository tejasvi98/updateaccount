package com.dojo.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.dojo.account.exception.ConstraintException;
import com.dojo.account.exception.InvalidUserException;
import com.dojo.account.exception.UserNotFoundException;
import com.dojo.account.model.CustomerDetailsDTO;
import com.dojo.account.model.SuccessResponse;
import com.dojo.account.service.AccountService;
@SpringBootTest
class AccountControllerTest {

    @InjectMocks
    AccountController accountController;
    
    @Mock
    AccountService accountService;
    
    @Test
    void updateUser() throws InvalidUserException, UserNotFoundException, ConstraintException {
        CustomerDetailsDTO user = new CustomerDetailsDTO("test", "test", "test", "test", "test", "test", "test", "test",
                1234567890, LocalDate.of(2020, 1, 8), "testAccount");
        String token = "testtesttest";
        SuccessResponse response = new SuccessResponse("successful",HttpStatus.OK, LocalDate.now());
        given(accountService.updateUser(token, user)).willReturn(response);
         SuccessResponse successResponse = accountController.updateAccount(token, user);
        assertEquals(response, successResponse);
    }

}
