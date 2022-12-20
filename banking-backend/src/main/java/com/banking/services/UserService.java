package com.banking.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.models.Account;
import com.banking.models.User;
import com.banking.models.UserType;

import com.banking.repositories.UserRepository;

import com.banking.exceptions.CannotUpdateUserException;
import com.banking.exceptions.EmailAlreadyExistsException;
import com.banking.exceptions.InvalidCredentialsException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserService {
	private UserRepository uRepo;
	
	/*public User registerUser(String userId, String firstName, String lastName, String email, String password, 
							 String address, String phoneNumber, UserType type, List<Account> accounts) {

		User u = new User(userId, firstName, lastName, email, password,
		address, phoneNumber, type, accounts);
		try {
			User ret = uRepo.save(u);
			return ret;
		} catch(Exception ex) {
			throw new EmailAlreadyExistsException();
		}
	}*/
	public User registerUser(User u) {
		try {
			User ret = uRepo.save(u);
			return ret;
		} catch(Exception ex) {
			throw new EmailAlreadyExistsException();
		}
	}
	
	public User loginUser(String email, String password) {
		User u = uRepo.getByEmail(email).orElseThrow(InvalidCredentialsException::new);
		
		if(!u.getPassword().equals(password)) {
			throw new InvalidCredentialsException();
		}
		
		return u;
		
	}
	
	public User updateUser(String firstName, String lastName, String email, String password) {
		
		User u = uRepo.getByEmail(email).orElseThrow(InvalidCredentialsException::new);
		
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setPassword(password);
		
		try {
			User ret = uRepo.save(u);
			return ret;
		} catch(Exception ex) {
			throw new CannotUpdateUserException();
		}
	}
	
	public User updatePassword(String email, String password) {
		
		User u = uRepo.getByEmail(email).orElseThrow(InvalidCredentialsException::new);
		
		u.setPassword(password);
		
		try {
			User ret = uRepo.save(u);
			return ret;
		} catch(Exception ex) {
			throw new CannotUpdateUserException();
		}
	}
	
	public User getUserByEmail(String email) {
		User u = null;
		
		try {
			return uRepo.getByEmail(email).get();
		}
		catch(NoSuchElementException e) {
			return u;
		}
		
	}
}
