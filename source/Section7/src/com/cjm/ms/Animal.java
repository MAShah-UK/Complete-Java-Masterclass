package com.cjm.ms;

/* When classes are created without the use of the extends keyword, they
* implicitly extend from the Object class. Thus all classes within Java
* are subclasses of Object. You can add extends Object to make it explicit,
* but it gives the same result and is redundant. */
public class Animal {

    // All animals, more or less, have these features.
    private String name;
    private int brain;
    private int body;
    private int size;
    private int weight;

    public Animal(String name, int brain, int body, int size, int weight) {
        this.name = name;
        this.brain = brain;
        this.body = body;
        this.size = size;
        this.weight = weight;
    }

    /* All animals have these behaviours.
    Public method can be accessed from inside this class, derived classes,
    and outside this class. */
    public void eat() {
        System.out.println("Animal.eat() called.");
    }

    public void move(int speed) {
        System.out.println("Animal.move() called. Animal is moving at " + speed);
    }

    public String getName() {
        return name;
    }

    public int getBrain() {
        return brain;
    }

    public int getBody() {
        return body;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
