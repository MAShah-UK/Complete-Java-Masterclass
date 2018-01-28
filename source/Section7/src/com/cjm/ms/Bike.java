package com.cjm.ms;

public class Bike extends Vehicle {

    public Bike(String model, boolean isWorking) {
        super(model, isWorking);
    }

    public Bike(String model, double velocity, double angle, int gear, boolean isWorking) {
        super(model, velocity, angle, gear, isWorking);
    }

    public void pedal(int velocity) {
        System.out.println("Bike is being pedalled.");
        move(velocity);
    }

    @Override
    public void makeSound() {
        System.out.println("RING-RING!");
    }

    public void playRadio() {
        System.out.println("Hello from the other side.");
    }
}
