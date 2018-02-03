package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        createPC();
        createRoom();
        createPlayerBad();
        createPlayerGood();
        createPrinter();
        createRandomMovies();
        createMotorbike();
        createBurger();
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
        System.out.println("\nBEGIN: createPlayerBad");

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
        System.out.println("\nBEGIN: createPlayerGood");

        PlayerGood player = new PlayerGood("Tim", 50, "Sword");
        System.out.println("Initial health is " + player.getHealth() + ".");
    }

    // Practice encapsulation via exercise.
    public static void createPrinter() {
        System.out.println("\nBEGIN: createPrinter");

        Printer printer = new Printer(50, 0, true);
        printer.print(190, true);
        printer.print(50, false);
    }

    // Practice polymorphism.
    public static Movie createRandomMovie() {
        // Returns integer in range 0 <= x <= 5.
        int randomNumber = (int) (Math.random()*5 + 1);
        System.out.println("Random number generated was " + randomNumber + ".");
        switch (randomNumber) { // break statement is redundant since method returns anyway in each case.
            case 1:
                return new Jaws();
            case 2:
                return new IndependenceDay();
            case 3:
                return new MazeRunner();
            case 4:
                return new StarWars();
            case 5:
                return new Forgettable();
        }

        return null; // Should never be executed.
    }

    // Practice polymorphism.
    public static void createRandomMovies() {
        System.out.println("\nBEGIN: createRandomMovies");

        for (int i = 0; i < 10; i++) {
            Movie movie = createRandomMovie();
            System.out.println("Movie #" + i + ": " + movie.getName() + "\n" +
                                "Plot: " + movie.plot());
        }
    }

    // Practice polymorphism via exercise.
    public static void createMotorbike() {
        System.out.println("\nBEGIN: createMotorbike");

        Motorbike mbike = new Motorbike("General Motorbike", 4, false);
        mbike.brake();
        mbike.setEngineWorking(true);
        mbike.accelerate(10.0, 2.5);
        mbike.brake();
        System.out.println();

        BMWRNineTPure bmw = new BMWRNineTPure(true);
        System.out.println(bmw.getName());
        bmw.startEngine();
        System.out.println();

        IndianScoutBobber bobber = new IndianScoutBobber(true);
        bobber.accelerate(5.1, 1.5);
    }

    // Practice OOP via exercise - encapsulation, inheritance, and polymorphism.
    public static void createBurger() {
        System.out.println("\nBEGIN: createBurger");

        Hamburger burger = new Hamburger("basic", "chicken", 5.00, 0.5);
        burger.addTopping("pickles");
        burger.addTopping("lettuce");
        burger.addTopping("tomato");
        burger.addTopping("mayo");
        burger.addTopping("ketchup");
        System.out.println("Current toppings: " + burger.getToppings());
        System.out.println("Burger price is: £" + burger.getTotalPrice());
        System.out.println();

        HealthyBurger healthyBurger = new HealthyBurger("beef", 5.00, 0.5);
        healthyBurger.addTopping("cheese");
        healthyBurger.addTopping("tomato");
        System.out.println("Burger price is: £" + healthyBurger.getToppingPrice());
        System.out.println();

        DeluxeBurger delBurger = new DeluxeBurger("deluxe", "lamb", 6.00, 0.5);
        System.out.println(delBurger.getToppings());
        delBurger.addTopping("cheese");
        System.out.println(delBurger.getTotalPrice());
    }
}
