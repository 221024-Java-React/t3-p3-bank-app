package com.banking.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;
import com.banking.app.exceptions.EmailAlreadyExistsException;
import com.banking.app.exceptions.InvalidCredentialsException;
import com.banking.app.models.User;
import com.banking.app.repositories.UserRepository;
import com.banking.app.services.UserService;
/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes= AppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
*/
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {
	@Mock
	UserRepository uRepo;
	
	@InjectMocks
	UserService uServ;
	
	//We want to be sure our database is clear after each test, so lets setup a @BeforeEach method to clear the database
	@BeforeEach
	public void resetDatabase() {
		System.out.println("Run before each test");
		uRepo.deleteAll();
	}
	
	@Test
	public void testSuccessfulRegistration() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555");
		//Optional<User> empty = Optional.empty();
		//roles.add(emp);
		uServ.registerUser(emp);
		assertNotNull(uServ.getUserByEmail(emp.getEmail()));
		assertEquals(uServ.getUserByEmail(emp.getEmail()),emp);
	}
	
	@Test
	public void testUnsuccessfulRegistration() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555");
		User empCopy = new User("Copy", "Other", "test@email.com", "1234 Test Rd.", "555-555-5555");
		//Optional<User> empty = Optional.empty();
		//roles.add(emp);
		uServ.registerUser(emp);
		Exception exception = assertThrows(EmailAlreadyExistsException.class, () ->
			uServ.registerUser(empCopy));
		assertEquals("This email address is not available. Please log in or register with a different email address.", exception.getMessage());
	}
	
	@Test
	public void testSuccessfulLogin() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555");
		uServ.registerUser(emp);
		assertEquals(uServ.loginUser("test@email.com","password"),emp);
	}
	
	@Test
	public void testUnsuccessfulLogin() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555", "password");
		uServ.registerUser(emp);
		//assertEquals(uServ.loginUser("test@email.com","password"),emp);
		Exception exception = assertThrows(InvalidCredentialsException.class, () ->
			uServ.loginUser("test@email.com","asdf"));
		assertEquals("Incorrect username or password.", exception.getMessage());
		Exception exception1 = assertThrows(InvalidCredentialsException.class, () ->
			uServ.loginUser("wrong@email.com","password"));
		assertEquals("Incorrect username or password.", exception1.getMessage());
	}
	
	@Test
	public void testGetUser() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555", "password");
		uServ.registerUser(emp);
		assertEquals(uServ.getUserByEmail("test@email.com"),emp);
		assertNull(uServ.getUserByEmail("wrong@email.com"));
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
}