package com.cjm.ms;

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

    public void move() {

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
