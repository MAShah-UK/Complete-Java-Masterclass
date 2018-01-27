package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        createCar();
        createTransactions();
        createVIP();
    }

    // Practice instantiating a class, and using getters and setters.
    public static void createCar() {

        System.out.println("BEGIN: createCar");

        Car porsche = new Car();
        porsche.setModel("911");
        System.out.println("Model is " + porsche.getModel());
    }

    // Practice using validation in methods, and constructors.
    public static void createTransactions() {

        System.out.println("\nBEGIN: createTransactions");

        // Create accounts using default constructor.
        BankAccount johnDoe = new BankAccount();
        johnDoe.setAccountNumber("00000001");
        johnDoe.setEmail("johndoe@email.com");
        johnDoe.setName("John Doe");
        johnDoe.setPhoneNumber("12345678901");
        johnDoe.deposit(100);
        System.out.println();

        BankAccount janeDoe = new BankAccount();
        janeDoe.setAccountNumber("00000002");
        janeDoe.setEmail("janedoe@email.com");
        janeDoe.setName("Jane Doe");
        janeDoe.setPhoneNumber("12345678902");
        janeDoe.deposit(50);
        System.out.println();

        // Create account using custom constructor.
        BankAccount jackDoe = new BankAccount("00000003", 200, "jackdoe@email.com",
                "12345678903", "Jack Doe");

        // Process transactions.
        johnDoe.withdraw(51);
        janeDoe.withdraw(51);
        jackDoe.withdraw(199);
    }

    //  Practice working with default/custom constructors.
    public static void createVIP() {

        System.out.println("\nBEGIN: createVIP");

        // Should output three default parameters.
        VIPCustomer johnDoe = new VIPCustomer();
        johnDoe.printState();
        System.out.println();

        // Should output only one default parameter.
        VIPCustomer janeDoe = new VIPCustomer("Jane Doe", "janedoe@email.com");
        janeDoe.printState();
        System.out.println();

        // Should not output any default parameters.
        VIPCustomer jackDoe = new VIPCustomer("Jack Doe", "jackdoe@email.com", 25000.0);
        jackDoe.printState();
    }

}
