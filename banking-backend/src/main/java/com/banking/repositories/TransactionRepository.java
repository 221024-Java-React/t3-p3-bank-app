package com.banking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.models.TransactionData;
import com.banking.models.TransactionType;
import com.banking.models.Account;
import com.banking.models.AccountType;
import com.banking.models.User;

public interface TransactionRepository extends JpaRepository<TransactionData, Integer> {
	List<TransactionData> getTransactionDatasByUser(User u);
	List<TransactionData> getTransactionDatasByAccount(Account a);
	/*	
	List<Ticket> getTicketsBySubmitterAndType(Employee e, TicketType t);
	List<Ticket> getTicketsBySubmitterAndStatus(Employee e, TicketStatus s);
	*/
	List<TransactionData> getTransactionDatasByType(TransactionType s);
	TransactionData getTransactionDataByTransactionDataId(int id);
}
