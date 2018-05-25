package com.cjm.ms.concurrency;

// Challenge 7:
// Spot and fix the problem.

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class C7BankAccount {
    private double balance;
    private String accountNumber;
    private Lock lock = new ReentrantLock();

    C7BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean withdraw(double amount) {
        if (lock.tryLock()) {
            try {
                // Simulate database access
                Thread.sleep(100);
                balance -= amount;
                System.out.printf("%s: Withdrew %f\n", Thread.currentThread().getName(), amount);
                return true;
            } catch (InterruptedException e) {
            } finally {
                // Originally this was missing. The lock will never have been released.
                // Thus two threads will compete for the same lock, but one never releases it,
                // the other never obtains it. This situation is a livelock since the threads
                // are never stuck in a blocked state and they keep retrying.
                lock.unlock();
            }
        }
        return false;
    }

    public boolean deposit(double amount) {
        if (lock.tryLock()) {
            try {
                // Simulate database access
                Thread.sleep(100);
                balance += amount;
                System.out.printf("%s: Deposited %f\n", Thread.currentThread().getName(), amount);
                return true;
            } catch (InterruptedException e) {
            } finally {
                // Same issue here.
                lock.unlock();
            }
        }
        return false;
    }

    public boolean transfer(C7BankAccount destinationAccount, double amount) {
        if (withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            }
            else {
                // The deposit failed. Refund the money back into the account.
                System.out.println(Thread.currentThread().getName() + ": Destination account busy. Refunding money.");
                deposit(amount);
            }
        }
        return false;
    }
}

class Transfer implements Runnable {
    private C7BankAccount sourceAccount, destinationAccount;
    private double amount;

    Transfer(C7BankAccount sourceAccount, C7BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        while (!sourceAccount.transfer(destinationAccount, amount))
            continue;
        System.out.printf("%s completed\n", Thread.currentThread().getName());
    }
}
