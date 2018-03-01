package com.cjm.ms;

// An abstract class can't be instantiated without being inherited from.
// Mark a class abstract when it's not useful on its own.
public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    // Abstract methods must be implemented in subclasses.
    public abstract void eat();
    public abstract void breath();

    public String getName() {
        return name;
    }
}
