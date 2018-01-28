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

    @Override
    public void eat() {
        System.out.println("Dog.eat() called.");
        chew();
        super.eat();
    }

    private void chew() {
        System.out.println("Dog.chew() called.");
    }
}
