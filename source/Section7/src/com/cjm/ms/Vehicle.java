package com.cjm.ms;

// Exercise:
// Start with a base class of a Vehicle, then create a Bike class that inherits from this base class.
// Finally, create another class, a specific type of Bike that inherits from the Bike class.
// You should be able to hand steering, changing gears, and moving (speed in other words).
// You will want to decide where to put the appropriate state and behaviours (fields and methods).
// As mentioned above, changing gears, increasing/decreasing speed should be included.
// For you specific type of vehicle you will want to add something specific for that type of bike.

public class Vehicle {
    // Fields that all vehicles should have.
    private String model;    // Defaults to null.
    private double velocity; // Defaults to 0.0;
    private double angle;
    private int gear;        // Defaults to 0.
    boolean isWorking;       // Defaults to false.

    public Vehicle(String model, boolean isWorking) {
        this.model = model;
        this.isWorking = isWorking;
    }

    public Vehicle(String model, double velocity, double angle, int gear, boolean isWorking) {
        this.model = model;
        this.velocity = velocity;
        this.angle = angle;
        this.gear = gear;
        this.isWorking = true;
    }

    private void displayDirection(double angle) {
        if (angle > 5.0) {
            System.out.println("Vehicle is going to the right.");
        } else if (angle < -5.0) {
            System.out.println("Vehicle is going to the left");
        } else {
            System.out.println("Vehicle is going straight.");
        }
    }

    public boolean adjustSteering(double deltaAngle) {
        if (!isWorking) {
            return false;
        }

        double newAngle = angle + deltaAngle;
        boolean validSteering = Math.abs(newAngle) <= 180.0;

        if (validSteering) {
            angle = newAngle;
            displayDirection(angle);
        }

        return validSteering;
    }

    public boolean changeGears(int newGear) {
        if (!isWorking) {
            return false;
        }

        boolean validGear = newGear >= 1 && newGear <= 6;

        if (validGear) {
            gear = newGear;
            System.out.println("Vehicle gear is " + gear + ".");
        }

        return validGear;
    }

    public boolean move(double velocity) {
        if (!isWorking) {
            return false;
        }

        this.velocity = velocity;
        System.out.println("Vehicle velocity is " + velocity + " m/s.");

        return true;
    }

    public void makeSound() {

    }

    public String getModel() {
        return model;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAngle() {
        return angle;
    }

    public int getGear() {
        return gear;
    }
}
