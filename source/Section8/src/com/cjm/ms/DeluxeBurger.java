package com.cjm.ms;

public class DeluxeBurger extends Hamburger {
    public DeluxeBurger(String breadRollType, String meat, double basePrice, double toppingPrice) {
        super(breadRollType, meat, basePrice, toppingPrice);
        maxToppings = 2;
        super.addTopping("chips");
        super.addTopping("drinks");
    }

    @Override
    public boolean addTopping(String topping) {
        System.out.println(getClass().getSimpleName() + " can't have additional toppings.");
        return false;
    }
}
