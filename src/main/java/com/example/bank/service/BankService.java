package com.example.bank.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.example.bank.service.UnknownAccountException;
import com.example.bank.service.InvalidAmountException;


@Service
public class BankService {

    private final Map<String, Account> db = new HashMap<>();


    public BankService() {
        db.put("1", new Account("1", "Ali", new BigDecimal("1000.00"), "EUR"));
        db.put("2", new Account("2", "Sara", new BigDecimal("2000.00"), "USD"));
    }

   
    public Account getAccount(String accountId) {
        return db.get(accountId);
    }

  
    public BigDecimal deposit(String accountId, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be > 0");
        }

        Account acc = db.get(accountId);

        if (acc == null) {
            throw new UnknownAccountException("Unknown accountId: " + accountId);
        }

        acc.balance = acc.balance.add(amount);
        return acc.balance;
    }

    
    public BigDecimal withdraw(String accountId, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be > 0");
        }

        Account acc = db.get(accountId);

        if (acc == null) {
            throw new UnknownAccountException("Unknown accountId: " + accountId);
        }


        acc.balance = acc.balance.subtract(amount);
        return acc.balance;
    }

}