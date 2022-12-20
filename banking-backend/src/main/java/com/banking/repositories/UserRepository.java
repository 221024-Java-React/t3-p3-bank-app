package com.banking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> getByEmail(String email);
}
