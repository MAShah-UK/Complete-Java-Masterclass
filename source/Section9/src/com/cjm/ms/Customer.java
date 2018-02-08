package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Double> transactions = new ArrayList<>();

    public Customer(String name, Double initialTransaction) {
        this.name = name;
        transactions.add(initialTransaction);
    }

    public void addTransactions(Double transaction) {
        transactions.add(transaction);
    }
}
