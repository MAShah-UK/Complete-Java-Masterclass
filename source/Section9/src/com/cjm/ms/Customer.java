package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private double balance;
    private List<Double> transactions = new ArrayList<>();

    public Customer(String name, double initBalance) {
        this.name = name;
        addTransaction(initBalance);
    }

    public boolean addTransaction(double transaction) {
        double newBal = balance + transaction;
        boolean isValid = newBal > 0;
        if (isValid) {
            transactions.add(transaction);
            balance = newBal;
        }
        return isValid;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public List<Double> getTransactions() {
        return transactions;
    }
}
