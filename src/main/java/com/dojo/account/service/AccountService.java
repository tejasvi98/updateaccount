package com.dojo.account.service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dojo.account.client.LoginClient;
import com.dojo.account.exception.ConstraintException;
import com.dojo.account.exception.InvalidUserException;
import com.dojo.account.exception.UserNotFoundException;
import com.dojo.account.model.AuthResponse;
import com.dojo.account.model.CustomerDetailsDTO;
import com.dojo.account.model.SuccessResponse;

@Service
public class AccountService {
	@Autowired
	LoginClient loginClient;

	public SuccessResponse updateUser(String token, CustomerDetailsDTO user)
			throws InvalidUserException, UserNotFoundException, ConstraintException {
		AuthResponse auth = loginClient.verifyToken(token).getBody();
		if (emptyFieldCheck(user))
			throw new ConstraintException("Fields are empty");
		if (Objects.nonNull(auth) && Objects.nonNull(auth.getUsername())
				&& auth.getUsername().equals(user.getUsername())) {

			if (!auth.isValid())
				throw new InvalidUserException("User is invalid");
			Optional<CustomerDetailsDTO> existingUser = Optional
					.ofNullable(loginClient.findById(token, user.getUsername()).getBody());
			if (!existingUser.isPresent())
				throw new UserNotFoundException("User not found");
			CustomerDetailsDTO updatedUser = existingUser.get();
			// below are the details which can be updated i.e password,address,contact
			// number,country,email,state
			if (passwordLengthValidation(user.getPassword()))
				throw new ConstraintException("password length is short or empty");
			if (!emailValidation(user.getEmail()))
				throw new ConstraintException("Email validation failed");
			updatedUser.setPassword(user.getPassword());
			updatedUser.setEmail(user.getEmail());

			updatedUser.setAddress(user.getAddress());
			updatedUser.setContactNumber(user.getContactNumber());
			updatedUser.setCountry(user.getCountry());
			updatedUser.setState(user.getState());
			return loginClient.updateDetails(updatedUser);
		}
		return new SuccessResponse("Logged in user trying to update other user's data", HttpStatus.BAD_REQUEST,
				LocalDate.now());
	}

	private boolean passwordLengthValidation(String password) {
		return password.length() < 8;
	}

	private boolean emailValidation(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean emptyFieldCheck(CustomerDetailsDTO customerDetailsDTO) {
		return Objects.isNull(customerDetailsDTO.getAddress()) && customerDetailsDTO.getContactNumber()==0L
				&& Objects.isNull(customerDetailsDTO.getCountry()) && Objects.isNull(customerDetailsDTO.getState());
	}
}
