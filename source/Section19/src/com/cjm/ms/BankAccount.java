package com.cjm.ms;

public class BankAccount {
    private String firstName;
    private String lastName;
    private double balance;
    private AccountType accountType;

    public enum AccountType {
        CHECKING,
        SAVINGS
    }
    public BankAccount(String firstName, String lastName, double balance) {
        this(firstName, lastName, balance, AccountType.CHECKING);
    }
    public BankAccount(
            String firstName, String lastName,
            double balance, AccountType accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }
    public boolean isChecking() {
        return accountType.equals(AccountType.CHECKING);
    }

    // branch: true  - transaction at branch with teller.
    //         false - transaction at an ATM.
    public double deposit(double amount, boolean branch) {
        balance += amount;
        return balance;
    }
    // branch: true  - transaction at branch with teller.
    //         false - transaction at an ATM.
    public double withdraw(double amount, boolean branch) {
        balance -= amount;
        return balance;
    }
}
