package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        createCar();
        createTransactions();
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
        johnDoe.setEmail("johndoe@emailservice.com");
        johnDoe.setName("John Doe");
        johnDoe.setPhoneNumber("12345678901");
        johnDoe.deposit(100);

        BankAccount janeDoe = new BankAccount();
        janeDoe.setAccountNumber("00000002");
        janeDoe.setEmail("janedoe@emailservice.com");
        janeDoe.setName("Jane Doe");
        janeDoe.setPhoneNumber("12345678902");
        janeDoe.deposit(50);

        // Create account using custom constructor.
        BankAccount jackDoe = new BankAccount("00000003", 200, "jackdoe@emailservice.com",
                "12345678903", "Jack Doe");

        // Process transactions.
        johnDoe.withdraw(51);
        janeDoe.withdraw(51);
        jackDoe.withdraw(199);
    }

}
