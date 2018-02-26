package com.cjm.ms;

public class Main {
    public static void main(String[] args) {
        createPhones();
        manageData();
    }

    // Practice working with interfaces.
    public static void createPhones() {
        System.out.println("\nBEGIN: createPhones");

        ITelephone timsPhone; // Can't instantiate an interface, e.g. new ITelephone().

        timsPhone = new DeskPhone(123456); // Uses polymorphism - recommended.
        timsPhone.powerOn();
        timsPhone.callPhone(123456);
        timsPhone.answer();

        timsPhone = new MobilePhone(24565); // Uses polymorphism again.
        timsPhone.powerOn();
        timsPhone.callPhone(24565);
        timsPhone.answer();
    }

    // Practice working with interfaces via exercise.
    public static void manageData() {
        System.out.println("\nBEGIN: manageData");

        System.out.println("Loading values for BasicGame.");
        BasicGame bGame = new BasicGame();

        System.out.println("\nLoading values for BasicEngineeringOrder.");
        BasicEngineeringOrder bEngOrder = new BasicEngineeringOrder();

        System.out.println("\nSaving BasicGame data.");
        ManageData.saveData(bGame);

        System.out.println("\nSaving BaseEngineeringOrder data.");
        ManageData.saveData(bEngOrder);
    }
}
