package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        createPC();
        createRoom();
        createPlayerBad();
        createPlayerGood();
    }

    // Practice composition by building a PC.
    public static void createPC() {
        System.out.println("\nBEGIN: createPC");

        Dimensions caseDims = new Dimensions(20, 20, 5);
        Case theCase = new Case("220B", "Dell", "240", caseDims);

        // Instantiate the resolution class directly in a method call. Since it won't be used elsewhere.
        Monitor monitor = new Monitor("27inch Beast", "Acer", 27, new Resolution(2540, 1440));

        Motherboard motherboard = new Motherboard("BJ-200", "ASUS", 4, 6, "v2.44");

        // Build PC using components we just built.
        PC pc = new PC(theCase, monitor, motherboard);
        // Use pc reference to grab references to it's other components.
//        pc.getMonitor().drawPixelAt(1500, 1200, "red");
//        pc.getMotherboard().loadProgram("Windows 1.0");
//        pc.getTheCase().pressPowerButton();
        pc.powerUp();
    }

    // Practice composition via exercise.
    public static void createRoom() {
        System.out.println("\nBEGIN: createRoom");

        Bed bed = new Bed(2, new Dimensions(200, 100, 25));
        FramedPicture picture = new FramedPicture("Picasso", new Dimensions(50, 25));

        Room bedroom = new Room(bed, picture);
        bedroom.getBed().sleep(1);
        bedroom.getBed().sleep(5);
        bedroom.admirePicture(5);
    }

    // Practice encapsulation - how not to do it.
    public static void createPlayerBad() {
        PlayerBad player = new PlayerBad();
        player.name = "Tim";
        player.health = 20;
        player.weapon = "Sword";

        int damage = 10;
        player.loseHealth(damage);
        System.out.println("Remaining health " + player.healthRemaining());

        damage = 11;
        player.health = 200; // Bad design. Takes control of logic away from Player class.
        player.loseHealth(damage);
        System.out.println("Remaining health " + player.healthRemaining());
    }

    // Practice encapsulation - how to do it.
    public static void createPlayerGood() {
        PlayerGood player = new PlayerGood("Tim", 50, "Sword");
        System.out.println("Initial health is " + player.getHealth() + ".");
    }
}
