package com.cjm.ms;

// Exercise:
// We are going to go back to the car analogy.
// Create a base class called Car.
// It should have a few fields that would be appropriate for a generic car class:
// engine, cylinders, wheels, etc.
// Constructor should initialize cylinders (number of) and name, and set wheels to 4,
// and engine to true. Cylinders and names would be passed parameters.
// Create appropriate getters.
// Create methods like startEngine, accelerate, and brake.
// Show a message for each in the base class.
// Now create 3 sub classes for your favorite vehicles.
// Override the appropriate methods to demonstrate polymorphism in use.
// Put all classes in the one java file (this one).

public class Motorbike {
    private String name;
    private int cylinders;
    private int wheels;
    private boolean engineWorks;
    private double velocity;

    public Motorbike(String name, int cylinders, boolean engineWorks) {
        this.name = name;
        this.cylinders = cylinders;
        this.wheels = 2;
        this.engineWorks = engineWorks;
    }

    public boolean startEngine() {
        System.out.println(engineWorks ? "Engine for motorbike has started." :
                                         "Engine failure.");
        return engineWorks;
    }

    public double accelerate(double acceleration, double time) {
        if (engineWorks) {
            velocity += acceleration * time;
            System.out.println("Motorbike velocity " + velocity + " m/s.");
        }
        return velocity;
    }

    public boolean brake() {
        if (engineWorks && Math.abs(velocity) > 0.5) {
            System.out.println("Motorbike is braking to a stop.");
            velocity = 0.0;
        } else {
            System.out.println("The engine isn't working, or the motorbike isn't moving.");
        }
        return engineWorks;
    }

    public String getName() {
        return name;
    }

    public int getCylinders() {
        return cylinders;
    }

    public int getWheels() {
        return wheels;
    }

    public boolean isEngineWorking() {
        return engineWorks;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setEngineWorking(boolean engineWorks) {
        this.engineWorks = engineWorks;
    }
}

class BMWRNineTPure extends Motorbike {
    public BMWRNineTPure(boolean engineWorks) {
        super("BMW R Nine T Pure", 2, engineWorks);
    }

    // Overridden method that replaces the original method.
    @Override
    public boolean startEngine() {
        // Assumes engine always works.
        System.out.println("BMW started up!");
        return true;
    }
}

class IndianScoutBobber extends Motorbike {
    public IndianScoutBobber(boolean engineWorks) {
        super("Indian Scout Bobber", 4, engineWorks);
    }

    // Overridden method that also calls the original implementation.
    @Override
    public double accelerate(double acceleration, double time) {
        System.out.println("The Bobber accelerates like a normal bike!");
        super.accelerate(acceleration, time);
        return getVelocity();
    }
}
