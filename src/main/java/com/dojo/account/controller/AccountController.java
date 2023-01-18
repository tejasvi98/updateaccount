package com.dojo.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dojo.account.exception.ConstraintException;
import com.dojo.account.exception.InvalidUserException;
import com.dojo.account.exception.UserNotFoundException;
import com.dojo.account.model.CustomerDetailsDTO;
import com.dojo.account.model.SuccessResponse;
import com.dojo.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AccountController {

	@Autowired
	private AccountService accountService;

	
	@PutMapping("update")
	public SuccessResponse updateAccount(@RequestHeader("Authorization") String token,@RequestBody CustomerDetailsDTO customer) throws InvalidUserException, UserNotFoundException, ConstraintException {
		log.debug("updating account details");
		return accountService.updateUser(token,customer);
	}
}
