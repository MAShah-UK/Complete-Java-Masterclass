package com.cjm.ms;

public class Car {
    // Mark fields as private for encapsulation / data hiding.
    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;

    // Use public getters and setters to allow putting logic between setting/getting fields.
    // This could be for verification, validation, logging, raising events/exceptions, etc.
    public void setModel(String model) {
        String validModel = model.toLowerCase();
        if(validModel.equals("carrera") || validModel.equals("commodore")) {
            this.model = model;
        } else {
            this.model = "Unknown";
        }
    }

    // Use getters and setters even if there is no obvious use for it. If eventually you need
    // to add logic, then you will not inconvenience users by requiring them to change their code.
    public String getModel() {
        return model;
    }
}
