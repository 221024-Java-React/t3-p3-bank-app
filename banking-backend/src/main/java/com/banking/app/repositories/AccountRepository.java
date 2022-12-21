package com.banking.app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.app.models.Account;
import com.banking.app.models.AccountType;
import com.banking.app.models.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	List<Account> getAccountsBySubmitter(User u);
	/*List<Ticket> getTicketsBySubmitterAndType(Employee e, TicketType t);
	List<Ticket> getTicketsBySubmitterAndStatus(Employee e, TicketStatus s);*/
	List<Account> getAccountsByType(AccountType s);
	Account getAccountByAccountId(UUID accountId);
}
