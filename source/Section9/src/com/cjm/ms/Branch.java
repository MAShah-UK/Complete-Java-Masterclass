package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String name;
    private List<Customer> customers = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    private Customer findCustomer(String name) {
        for (Customer cust : customers) {
            if (cust.getName().equals(name)) {
                return cust;
            }
        }
        return null;
    }

    public void addTransaction(String name, double transaction) {
        Customer cust = findCustomer(name);
        if (cust == null) {
            customers.add(new Customer(name, transaction));
        } else {
            System.out.println(cust.addTransaction(transaction) ?
                               "Transaction for " + name + " was processed." :
                               "Transaction for " + name + " could not be processed.");
        }
    }

    public String getName() {
        return name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
