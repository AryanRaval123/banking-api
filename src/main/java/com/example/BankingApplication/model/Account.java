package com.example.BankingApplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false)
    private String accountNumber;

    private String accountHolderName;
    private String accountType;
    private Double balance;
    private LocalDateTime createdAt;

    // spring uses the dafault constructor, getter and setter method.

    public Account() {
        this.balance = 0.0;
        this.accountNumber = generateAccountNumber();
        this.createdAt = LocalDateTime.now();
    }

    // Your constructor → For when YOU create Account objects manually (your way)
    public Account(String accountHolderName, String accountType) {
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = 0.0;
        this.accountNumber = generateAccountNumber();
        this.createdAt = LocalDateTime.now();
    }

    public String generateAccountNumber(){
        return String.valueOf((long)((Math.random()*9_000_000_000L)+1_000_000_000L));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
