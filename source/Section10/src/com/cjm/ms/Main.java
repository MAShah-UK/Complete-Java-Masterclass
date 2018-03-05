package com.cjm.ms;

import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //createPhones();
        //manageData();
        //createGearbox();
        //createButton();
        //createMusicManager();
        //createDog();
        createDoubleLinkedList();
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
        ManageData.saveDataFromObject(bGame);

        System.out.println("\nSaving BaseEngineeringOrder data.");
        ManageData.saveDataFromObject(bEngOrder);
    }

    // Practice working with inner classes.
    public static void createGearbox() {
        System.out.println("\nBEGIN: createGearbox");

        Gearbox mcLaren = new Gearbox(6);

        // Must use outer class to access inner class.
        // Gearbox.Gear first = mcLaren.new Gear(1, 12.3); // Use this syntax for public non-static inner class.
        // Gearbox.Gear second = new Gearbox.Gear(2, 15.4); // Use this syntax for public static inner class.
        // Gearbox.Gear first = Gearbox.new Gear(1, 12.3); // Won't work.
        // Gearbox.Gear third = new mcLaren.Gear(3, 17.8); // Won't work.

        mcLaren.operateClutch(true);
        mcLaren.changeGear(1);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(1000));
        mcLaren.changeGear(2);
        System.out.println(mcLaren.wheelSpeed(3000));
        mcLaren.operateClutch(true);
        mcLaren.changeGear(3);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(6000));
    }

    public static void createButton() {

        Button btnPrint = new Button("Print");

        // This is a local class since it's a class defined within a method.
        class ClickListener implements Button.IOnClickListener {
            public ClickListener() {
                System.out.println("I have been attached.");
            }

            @Override
            public void onClick(String title) {
                System.out.println("(Local class) " + title + " was clicked.");
            }
        }

        btnPrint.setOnClickListener(new ClickListener());
        btnPrint.onClick(); // Simulates button being pressed.

        // This is an anonymous class since it doesn't have a name.
        // The generated anonymous class is a subclass of the interface, but it's nameless.
        Button.IOnClickListener btnPrintAction = new Button.IOnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println("(Anonymous class) " + title + " was clicked.");
            }
        };

        btnPrint.setOnClickListener(btnPrintAction);
        btnPrint.onClick();
    }

    // Practice inner classes via exercise.
    public static void createMusicManager() {
        System.out.println("\nBEGIN: createMusicManager");

        MusicManager music = new MusicManager();
        music.startInteractiveMenu();
    }

    // Practice working with abstract classes.
    public static void createDog() {
        System.out.println("\nBEGIN: createDog");

        Dog dog = new Dog("Yorkie");
        dog.breath();
        dog.eat();
        System.out.println();

        Parrot parrot = new Parrot("Austrailian Ringneck");
        parrot.breath();
        parrot.eat();
        parrot.fly();
        System.out.println();

        Penguin penguin = new Penguin("Emperor");
        penguin.fly();
    }

    // Practice abstract class via exercise.
    public static void createDoubleLinkedList() {
        System.out.println("\nBEGIN: createDoubleLinkedList");

        String citiesStr = "London Budapest Karachi WashingtonDC Lahore Paris";
        System.out.println("Original cities string: " + citiesStr);

        // Test sorted doubly linked list.
        DoublyLinkedList cities = new DoublyLinkedList();
        cities.add(citiesStr);
        System.out.println("Sorted cities list: " + cities.toString());
        System.out.println("3rd element in list: " + cities.get(2));
        cities.remove(2);
        System.out.println("Sorted cities list (3rd element removed): " + cities.toString());
        System.out.println();

        // Test binary search tree.

    }
}
