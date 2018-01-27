package com.cjm.ms;

// Exercise:
// Create a new class for a bank account.
// Create fields for the account number, balance, customer name, email and phone number.
// Create getters and setters for each field.
// Create two additional methods:
//  1. To allow the customer to deposit funds (this should increment the balance field).
//  2. To allow the customer to withdraw funds. This should deduct from the balance field,
// but do not allow the withdrawal to complete if there are insufficient funds.
// You will want to create various code in the Main class (the one created by IntelliJ) to
// confirm your code is working.
// Add some System.out.println's in the two methods above as well.

public class BankAccount {
    private String accountNumber;
    private double balance;
    private String email;
    private String phoneNumber;
    private String name;

    public BankAccount() {
        // Uses the custom constructor to pass default arguments.
        this("", 0, "", "", "");
        System.out.println("Default constructor called.");
    }

    public BankAccount(String email, String phoneNumber, String name) {
        // If some parameters are provided then pass those along with some default arguments.
        this("", 0, email, phoneNumber, name);
    }

    public BankAccount(String accountNumber, double balance, String email, String phoneNumber, String name) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double amount) {
        System.out.println(name + ": The old balance is: " + balance);
        balance += amount;
        System.out.println(name + ": The new balance is: " + balance);
    }

    // Returns true for successful processing, otherwise false.
    public boolean withdraw(double amount) {
        double newBalance = balance - amount;
        if (newBalance >= 0) {
            balance = newBalance;
            System.out.println(name + ": New balance was over zero and saved.");
            return true;
        } else {
            System.out.println(name + ": New balance was under zero and discarded.");
            return false;
        }
    }
}
