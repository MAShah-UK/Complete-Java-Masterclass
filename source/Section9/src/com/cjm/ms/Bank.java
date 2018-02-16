package com.cjm.ms;

// Exercise:
// Your job is to create a simple banking application.
// There should be a Bank class.
// It should have an ArrayList of branches.
// Each Branch should have an ArrayList of customers.
// The Customer class should have an ArrayList of Doubles (transactions).
// Customer:
//    Name, and the ArrayList of Doubles.
// Branch:
//    Need to be able to add a new customer and initial transaction amount.
//    Also needs to add additional transactions for that customer/branch.
// Bank:
//    Add a new branch.
//    Add a customer to that branch with initial transaction.
//    Add a transaction for an existing customer for that branch.
//    Show a list of customers for a particular branch and optionally a list
//    of their transactions.
// Demonstration autoboxing and unboxing in your code.
// Hint: Transactions
//    Add data validation.
//    e.g. check if exists, or does not exist, etc.
//    Think about where you are adding the code to perform certain actions.

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Branch> branches = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public void addBranch(String name) {
        branches.add(new Branch(name));
    }

    private Branch findBranch(String branchName) {
        for (Branch branch : branches) {
            if (branch.getName().equals(branchName)) {
                return branch;
            }
        }
        return null;
    }

    public void addTransaction(String branchName, String custName, double transaction) {
        Branch branch = findBranch(branchName);
        if (branch != null) {
            branch.addTransaction(custName, transaction);
        } else {
            System.out.println("Branch " + branchName + " doesn't exist.");
        }
    }

    public void listCustomers(boolean showTransactions) {
        System.out.println("Branches in " + getName());
        for (Branch branch : branches) {
            System.out.println("  Customers in " + branch.getName() + " branch:");
            for (Customer cust : branch.getCustomers()) {
                System.out.println("    • Balance for " + cust.getName() + " is " + cust.getBalance());
                if (showTransactions) {
                    for (double trans : cust.getTransactions()) {
                        String type = trans > 0 ? "Deposit" : "Withdraw";
                        System.out.println("      " + type + ": " + Math.abs(trans));
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }
}
