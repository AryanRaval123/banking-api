package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.Account;
import com.example.BankingApplication.model.TransferRequest;
import com.example.BankingApplication.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banking")
public class BankingController {

    @Autowired
    BankingService bankingService;


    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        // @RequestBody → Uses no-arg constructor + setters (Spring's way)
        Account newAccount = bankingService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("getAllAccounts")
    public ResponseEntity<List<Account>> getAllAccounts(){

        List<Account> accountList = bankingService.getAllAccounts();
        return ResponseEntity.ok(accountList);
    }

    @PostMapping("getAccountByNumber/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable("accountNumber")
                                                          String accountNumber){
        Account account = bankingService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping("deposit/{accountNumber}/{amount}")
    public ResponseEntity<Account> deposit(@PathVariable("accountNumber") String accountNumber,
                                           @PathVariable("amount") double amount){

        Account updatedAccount = bankingService.deposit(accountNumber,amount);
        if(updatedAccount!=null){
            return ResponseEntity.ok(updatedAccount);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("withdraw/{accountNumber}/{amount}")
    public ResponseEntity<Account> withdraw(@PathVariable("accountNumber") String accountNumber,
                                            @PathVariable("amount") double amount) {

        Account updatedAccount = bankingService.withdraw(accountNumber,amount);
        if(updatedAccount != null){
            return ResponseEntity.ok(updatedAccount);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest){

        boolean success = bankingService.transferMoney(transferRequest);
        return success ? ResponseEntity.ok("Money Transfer Successfully") :
                ResponseEntity.badRequest().body("Tranfer failed");
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountNumber") String accountNumber){

        boolean isdelete = bankingService.deleteAccount(accountNumber);
        return isdelete ? ResponseEntity.ok("Delete Successful") : ResponseEntity.badRequest().body("User doesn't exists");

    }



}
