package com.cjm.ms;

public class Dog extends Animal {
    // All dogs have these features, whereas not all animals do.
    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;

    // Initialise animal fields, then dog fields.
    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        /* Calls a constructor in the superclass.
         Like this(...), it must be the first statement.
         This is because the superclass must be initialised before the
         subclass in memory. */
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }

    private void chew() {
        System.out.println("Dog.chew() called.");
    }

    @Override
    public void eat() {
        System.out.println("Dog.eat() called.");
        chew();
        super.eat();
    }

    public void walk() {
        System.out.println("Dog.walk() called.");
        /* It is better to leave move as move, and not as super.move(), as the
        program will automatically use the 'latest' definition. */
        move(5);
    }

    public void run() {
        System.out.println("Dog.run() called.");
        move(10);
    }

    public void moveLegs(int speed) {
        System.out.println("Dog.moveLegs() called.");
    }

    @Override
    public void move(int speed) {
        System.out.println("Dog.move() called.");
        moveLegs(speed);
        // Have to use super.move() here, otherwise there will be infinite recursion.
        super.move(speed);
    }
}
