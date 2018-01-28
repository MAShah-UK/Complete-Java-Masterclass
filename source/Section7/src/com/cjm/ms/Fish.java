package com.cjm.ms;

public class Fish extends Animal {
    private int fish;
    private int gills;
    private int fins;

    public Fish(String name, int size, int weight, int fish, int gills, int fins) {
        super(name, 1, 1, size, weight);
        this.fish = fish;
        this.gills = gills;
        this.fins = fins;
    }

    private void rest() {

    }

    private void moveMuscles() {

    }

    private void moveBackFin() {

    }

    private void swim(int speed) {
        moveMuscles();
        moveBackFin();
        super.move(speed);
    }
}
