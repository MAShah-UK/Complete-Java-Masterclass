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
    private List<Branch> branches = new ArrayList<>();

    public void addBranch() {

    }

    public void addBranch(Customer cust) {

    }

    public void addTransaction(String name, String customer) {

    }
}
