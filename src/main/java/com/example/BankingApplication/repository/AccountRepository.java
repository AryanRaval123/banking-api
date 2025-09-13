package com.example.BankingApplication.repository;

import com.example.BankingApplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByAccountNumber(String accountNumber);

}
