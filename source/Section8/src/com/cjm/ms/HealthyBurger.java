package com.cjm.ms;

public class HealthyBurger extends Hamburger {
    public HealthyBurger(String meat, double basePrice, double toppingPrice) {
        super("Brown Rye", meat, basePrice, toppingPrice);
        this.maxToppings = 1;
    }
}
