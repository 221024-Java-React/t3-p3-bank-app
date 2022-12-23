package com.banking.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.LinkedHashMap;

import org.junit.BeforeClass;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import com.banking.app.models.User;
import com.banking.app.repositories.UserRepository;
import com.banking.app.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes= AppApplication.class)
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserService uServ;
	
	@Autowired
	UserRepository uRepo;
	
	private ObjectMapper om = new ObjectMapper();
	
	@BeforeClass
	public void resetDatabase() {
		System.out.println("Run before each test");
		uRepo.deleteAll();
		}
	
	
	
	@Test
	@Transactional
	public void testSuccessfulRegister() throws Exception {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("firstName", "first");
		map.put("lastName", "last");
		map.put("email", "name@email.com");
		map.put("address", "123 streetname");
		map.put("phoneNumber", "34274763");
		map.put("accountType", "checking");
		
		String context = om.writeValueAsString(map);
		mockMvc.perform(post("/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(context))
		.andDo(print())
		.andReturn();
		
		User registered = uRepo.getByEmail("name@email.com").get();
		assertEquals("first", registered.getFirstName());
		assertEquals("last", registered.getLastName());
		assertEquals("name@email.com", registered.getEmail());
		assertEquals("123 streetname", registered.getAddress());
		assertEquals("34274763", registered.getPhoneNumber());
		
		System.out.println(registered.getUserId());
		System.out.println(registered.getPassword());
		
	}
	
	@Test()
	@Transactional
	public void testfailedRegister() throws Exception{
		
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("firstName", "first");
		map.put("lastName", "last");
		map.put("email", "name@email.com");
		map.put("address", "123 streetname");
		map.put("phoneNumber", "34274763");
		map.put("accountType", "checking");
		String context = om.writeValueAsString(map);
		
		mockMvc.perform(post("/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(context))
		.andDo(print())
		.andReturn();
		
		
	}

}
