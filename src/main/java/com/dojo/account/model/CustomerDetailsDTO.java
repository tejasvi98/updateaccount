package com.dojo.account.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerDetailsDTO {

	private String username;
	@NotBlank
	private String password;
    private String name;
	@NotBlank
    private String address;
	@NotBlank
    private String state;
	@NotBlank
    private String country;
	@NotBlank
	@Email(message = "email is not valid")
    private String email;
    private String PAN;
    private long contactNumber;
    private LocalDate DOB;
    private String accountType;
	
	
}
