package com.banking.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.banking.app.repositories.UserRepository;
import com.banking.app.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes= AppApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserService uServ;
	
	@Autowired
	UserRepository urepo;
	
	
	
	
	@Test
	public void testRegister() throws Exception {
		fail("Not yet implemented");
	}

}
