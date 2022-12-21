package com.banking.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.app.models.Account;
import com.banking.app.models.AccountType;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;
import com.banking.app.models.User;

public interface TransactionRepository extends JpaRepository<TransactionData, Integer> {
	List<TransactionData> getTransactionDataByUser(User u);
	List<TransactionData> getTransactionDataByAccount(Account a);
		
	/*List<Ticket> getTicketsBySubmitterAndType(Employee e, TicketType t);
	List<Ticket> getTicketsBySubmitterAndStatus(Employee e, TicketStatus s);*/
	
	List<TransactionData> getTransactionDataByType(TransactionType s);
	TransactionData getTransactionDataByTransactionDataId(int id);
}
