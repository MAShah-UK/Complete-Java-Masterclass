package com.cjm.ms;

// Exercise:
// Create a new class called VIPCustomer.
// It should have three fields: name, credit limit, and email address.
// Create three constructors:
//  1st constructor empty should call the constructor with 3 parameters with default values.
//  2nd constructor should pass on the 2 values it receives and add a default value for the 3rd.
//  3rd constructor should save all fields.
// Create getters only for this using code generation as setters will not be needed.
// Test and confirm that it works.

public class VIPCustomer {
    String name;
    String emailAddress;
    double creditLimit;

    public VIPCustomer() {
        this("defaultName", "defaultEmail", 0.0);
    }

    public VIPCustomer(String name, String emailAddress) {
        this(name, emailAddress, 0.0);
    }

    public VIPCustomer(String name, String emailAddress, double creditLimit) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.creditLimit = creditLimit;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void printState() {
        System.out.println("Customer name: " + name);
        System.out.println("Customer email address:" + emailAddress);
        System.out.println("Customer credit limit: " + creditLimit);
    }
}
