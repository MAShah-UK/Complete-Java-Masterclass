package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        createCar();
        createTransactions();
    }

    // Practice initialising a class, and using getters and setters.
    public static void createCar() {

        System.out.println("BEGIN: createCar");

        Car porsche = new Car();
        porsche.setModel("911");
        System.out.println("Model is " + porsche.getModel());
    }

    // Practice using validation in getters and setters.
    public static void createTransactions() {

        System.out.println("BEGIN: createTransactions");

        // Create accounts.
        BankAccount johnDoe = new BankAccount();
        johnDoe.setAccountNumber(1);
        johnDoe.setBalance(100);
        johnDoe.setEmail("johndoe@emailservice.com");
        johnDoe.setName("John Doe");
        johnDoe.setPhoneNumber("12345678901");

        BankAccount janeDoe = new BankAccount();
        janeDoe.setAccountNumber(2);
        janeDoe.setBalance(50);
        janeDoe.setEmail("janedoe@emailservice.com");
        janeDoe.setName("Jane Doe");
        janeDoe.setPhoneNumber("12345678902");

        // Create transaction.
        johnDoe.withdraw(51);
        janeDoe.withdraw(51);
    }

}
