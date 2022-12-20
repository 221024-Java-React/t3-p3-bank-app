package com.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.models.User;
import com.banking.models.Account;
import com.banking.models.AccountType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	List<Account> getAccountsBySubmitter(User u);
/*	List<Ticket> getTicketsBySubmitterAndType(Employee e, TicketType t);
	List<Ticket> getTicketsBySubmitterAndStatus(Employee e, TicketStatus s);*/
	List<Account> getAccountsByType(AccountType s);
	Account getAccountByAccountId(String id);
}
