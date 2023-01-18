package com.dojo.account.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerDetailsDTOtest {
	@Autowired
	CustomerDetailsDTO customerDetailsDTO;

	@Test
	void setterTest() {
		customerDetailsDTO.setUsername("test");
		customerDetailsDTO.setPassword("test");
		customerDetailsDTO.setName("name");
		customerDetailsDTO.setAddress("address");
		customerDetailsDTO.setState("state");
		customerDetailsDTO.setCountry("country");
		customerDetailsDTO.setEmail("email");
		customerDetailsDTO.setPAN("pan");
		customerDetailsDTO.setContactNumber(0);
		customerDetailsDTO.setDOB(LocalDate.now());
		customerDetailsDTO.setAccountType("accountType");
		assertEquals("test",customerDetailsDTO.getUsername());
	}

	@Test
	void getterTest() {
		customerDetailsDTO.setUsername("test");
		customerDetailsDTO.setPassword("test");
		customerDetailsDTO.setName("name");
		customerDetailsDTO.setAddress("address");
		customerDetailsDTO.setState("state");
		customerDetailsDTO.setCountry("country");
		customerDetailsDTO.setEmail("email");
		customerDetailsDTO.setPAN("pan");
		customerDetailsDTO.setContactNumber(0);
		customerDetailsDTO.setDOB(LocalDate.now());
		customerDetailsDTO.setAccountType("accountType");
		String str = customerDetailsDTO.getUsername() + customerDetailsDTO.getPassword() + customerDetailsDTO.getName()
				+ customerDetailsDTO.getAddress() + customerDetailsDTO.getState() + customerDetailsDTO.getCountry()
				+ customerDetailsDTO.getEmail() + customerDetailsDTO.getPAN() + customerDetailsDTO.getContactNumber()
				+ customerDetailsDTO.getDOB() + customerDetailsDTO.getAccountType();
		assertTrue(str.contains(customerDetailsDTO.getUsername()));
	}

}
