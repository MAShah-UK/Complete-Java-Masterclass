package com.cjm.ms;

// We could have two people using a joint bank account at the same time.
// Create and start two threads that use the same BankAccount instance and an initial balance of $1000.00.
// One will deposit $300.00 into the bank account, and then withdraw $50.00.
// The other will deposit $203.75 and then withdraw $100.00.

// Challenge 1: Create and start threads.
// Challenge 2: Make the class thread safe.
// Challenge 3: Make acccunt number methods thread safe.
//   (Trick challenge: Synchronization wasn't necessary since fields are only read.
//   it's inefficient to mark methods as synchronized unnecessarily.)
// Challenge 4: Use ReentrantLock.
// Challenge 5: Use tryLock() with a one second timeout.
// Challenge 6: Update code so that status variable is thread safe.
//  (Trick challenge: Local variables are thread safe since each thread maintains its own copy of the stack.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private Lock lock = new ReentrantLock();

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }

    public double getBalance() {
        return balance;
    }

//    // Challenge 2:
//    public synchronized void deposit(double amount) {
//        balance += amount;
//    }
//
//    public synchronized void withdraw(double amount) {
//        balance -= amount;
//    }

    public void deposit(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    status = true;
                    balance += amount;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("tryLock() timed out.");
            }
        } catch(InterruptedException e) {
        }

        System.out.println("Transaction status = " + status);
    }

    public void withdraw(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    status = true;
                    balance -= amount;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("tryLock() timed out.");
            }
        } catch(InterruptedException e) {
        }

        System.out.println("Transaction status = " + status);
    }
}
