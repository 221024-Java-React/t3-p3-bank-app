package com.banking.app.services;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.repositories.AccountRepository;
import com.banking.app.repositories.UserRepository;
import com.banking.app.repositories.TransactionRepository;
//import com.banking.exceptions.ClaimAlreadyProcessedException;
//import com.banking.exceptions.NotAnEmployeeException;

import com.banking.app.models.Account;
import com.banking.app.models.AccountType;
import com.banking.app.models.User;
import com.banking.app.models.TransactionData;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountService {
  private AccountRepository cRepo;
  private UserRepository uRepo;
  private TransactionRepository tRepo;

  /*
   * public Account createAccount(String email, String accountId, User user,
   * AccountType type, Integer balance) {
   * User u = uRepo.getByEmail(email).get();
   * Account acc = new Account(accountId, u, type, balance);
   * return cRepo.save(acc);
   * }
   */
  public Account createAccount(Account a) {
    // User u = uRepo.getByEmail(email).get();
    // Account acc = new Account(accountId, u, type, balance);
    return cRepo.save(a);
  }

  public List<Account> getAccounts(int accountId) {
    User u = uRepo.findById(accountId).get();

    return cRepo.getAccountsBySubmitter(u);
  }

  public Account getAccountById(String id) {
    return cRepo.getAccountByAccountId(id);
  }

  public List<Account> getAccountsByType(AccountType s) {

    return cRepo.getAccountsByType(s);
  }

  public TransactionData transferBetweenAccounts(String accountIdFrom, String accountIdTo, Double amount) {
    Account from = cRepo.getAccountByAccountId(accountIdFrom);
    Account to = cRepo.getAccountByAccountId(accountIdTo);

    double fromA = from.getBalance() - amount;
    from.setBalance(fromA);
    double toA = to.getBalance() + amount;
    to.setBalance(toA);
    cRepo.save(from);
    cRepo.save(to);
    LocalDateTime time = LocalDateTime.now();

    TransactionData tFrom = new TransactionData();
    tFrom.setAccount(fromAccountId);
    tFrom.setAmount(amount);
    // tFrom.setType(TransactionType.Withdraw);
    tFrom.setDatetime(time);

    TransactionData tTo = new TransactionData();
    tTo.setAccount(to);
    tTo.setAmount(amount);
    // tFrom.setType(TransactionType.DEPOSIT);
    tFrom.setDatetime(time);

    tRepo.save(tTo);
    tRepo.save(tFrom);

    return tFrom;
  }
}
