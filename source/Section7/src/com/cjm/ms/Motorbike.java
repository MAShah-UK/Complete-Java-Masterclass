package com.cjm.ms;

public class Motorbike extends Bike {

    public Motorbike(String model, boolean isWorking) {
        super(model, isWorking);
    }

    public Motorbike(String model, double velocity, double angle, int gear, boolean isWorking) {
        super(model, velocity, angle, gear, isWorking);
    }

    public void accelerate(int velocity) {
        System.out.println("Motorbike is accelerating.");
        move(velocity);
        
        double speed = Math.abs(velocity);

        // Automatically change gears.
        if (speed >= 0 && speed < 10) {
            changeGears(1);
        } else if (speed >= 10 && speed < 20) {
            changeGears(2);
        } else if (speed >= 20 && speed < 30) {
            changeGears(3);
        } else if (speed >= 30 && speed < 40) {
            changeGears(4);
        } else if (speed >= 40 && speed < 50) {
            changeGears(5);
        } else {
            changeGears(6);
        }
    }

    @Override
    public void makeSound() {
        System.out.println("PEEP-PEEP!");
    }
}
