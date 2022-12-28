package com.banking.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.app.models.Account;
import com.banking.app.models.TransactionData;
import com.banking.app.models.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionData, Integer> {
  //List<TransactionData> getTransactionDataByUser(User u);

  List<TransactionData> getTransactionDataByAccount(Account a);

  /*
   * List<Ticket> getTicketsBySubmitterAndType(Employee e, TicketType t);
   * List<Ticket> getTicketsBySubmitterAndStatus(Employee e, TicketStatus s);
   */
  List<TransactionData> getTransactionDataByType(TransactionType s);

  TransactionData getTransactionDataByTransactionId(int id);
}

