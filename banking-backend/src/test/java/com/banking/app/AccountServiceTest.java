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
import com.banking.app.models.Account;
import com.banking.app.models.User;
import com.banking.app.repositories.AccountRepository;
import com.banking.app.repositories.UserRepository;
import com.banking.app.services.AccountService;
import com.banking.app.services.UserService;
/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes= AppApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
*/
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class AccountServiceTest {
	@Mock
	AccountRepository aRepo;
	
	@InjectMocks
	AccountService aServ;
	
	//We want to be sure our database is clear after each test, so lets setup a @BeforeEach method to clear the database
	@BeforeEach
	public void resetDatabase() {
		//System.out.println("Run before each test");
		aRepo.deleteAll();
	}
	
	@Test
	public void testSuccessfulAccountCreate() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555", "password");
		Account a = new Account("CHECKING", emp, 102.23);
		//Optional<User> empty = Optional.empty();
		//roles.add(emp);
		aServ.createAccount(a);
		assertNotNull(aServ.getAccountById(a.getAccountId()));
		assertEquals(aServ.getAccountById(a.getAccountId()),a);
	}
	/*
	@Test
	public void testUnsuccessfulRegistration() throws Exception {
		//List<User> roles = new ArrayList<>();
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555", "password");
		User empCopy = new User("Copy", "Other", "test@email.com", "1234 Test Rd.", "555-555-5555", "password");
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
		User emp = new User("First", "Last", "test@email.com", "1234 Test Rd.", "555-555-5555", "password");
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
		System.out.println(emp);
		System.out.println(uServ.getUserByEmail("test@email.com"));
		assertEquals(emp,uServ.getUserByEmail("test@email.com"));
		assertNull(uServ.getUserByEmail("wrong@email.com"));
	}
	*/
	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
}