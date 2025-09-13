package com.example.BankingApplication.service;

import com.example.BankingApplication.model.Account;
import com.example.BankingApplication.model.TransferRequest;
import com.example.BankingApplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankingService {

    @Autowired
    private AccountRepository accouuntRepository;

    public Account createAccount(Account account){
        return accouuntRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return accouuntRepository.findAll();
    }

    public Account getAccountByNumber(String accountNumber){
        return accouuntRepository.findByAccountNumber(accountNumber);
    }


    @Transactional
    public Account deposit(String accountNumber,double amount){

        Account account = accouuntRepository.findByAccountNumber(accountNumber);

        if(account!=null && amount > 0){
            account.setBalance(account.getBalance() + amount);
            accouuntRepository.save(account);
        }
        return account;
    }

    @Transactional
    public Account withdraw(String accountNumber,double amount){

        Account account = accouuntRepository.findByAccountNumber(accountNumber);

        if(account!=null && amount > 0){

            if(account.getBalance() >= amount){
                account.setBalance(account.getBalance() - amount);
                accouuntRepository.save(account);
            }
        }
        return account;

    }

    @Transactional
    public boolean transferMoney(TransferRequest transferRequest){

        Account fromAccount, toAccount;

        fromAccount = accouuntRepository.findByAccountNumber(transferRequest.getFromAccount());
        toAccount = accouuntRepository.findByAccountNumber((transferRequest.getToAccount()));

        if(fromAccount!=null && toAccount!=null && transferRequest.getAmount() > 0){

            if(fromAccount.getBalance() >= transferRequest.getAmount()){

                fromAccount.setBalance(fromAccount.getBalance() - transferRequest.getAmount());

                toAccount.setBalance(toAccount.getBalance() + transferRequest.getAmount());

                accouuntRepository.save(fromAccount); // Apply changes in the database
                accouuntRepository.save(toAccount);

                return true;
            }
        }
        return false;
    }
    @Transactional
    public boolean deleteAccount(String accountNumber){

        // find the user
        Account deleteAccount = accouuntRepository.findByAccountNumber(accountNumber);
        if(deleteAccount!=null){
            accouuntRepository.delete(deleteAccount);
            return true;
        }
        else{
            return false;
        }

    }

}
