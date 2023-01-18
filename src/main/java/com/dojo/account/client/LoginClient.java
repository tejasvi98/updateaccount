package com.dojo.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dojo.account.model.AuthResponse;
import com.dojo.account.model.CustomerDetailsDTO;
import com.dojo.account.model.SuccessResponse;

// creating connection with login Microservice
@FeignClient(url = "${login.feign.client}", name = "${login.feign.name}")
public interface LoginClient {
	@RequestMapping(path = "/validate", method = RequestMethod.GET)
	public ResponseEntity<AuthResponse> verifyToken(
			@RequestHeader(name = "Authorization", required = true) String token);
	//finds the user from database and return the same user details
	@PostMapping("/finduser/{username}")
	public ResponseEntity<CustomerDetailsDTO> findById(@RequestHeader("Authorization") String token,@PathVariable String username);
	
	@PutMapping("updateDetails")
	public SuccessResponse updateDetails(@RequestBody CustomerDetailsDTO customer);
}
