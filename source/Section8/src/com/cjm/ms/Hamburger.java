package com.cjm.ms;

// The purpose of the application is to help a fictitious company called Bills Burgers to manage
// their process of selling hamburgers.
// Our application will help Bill to select types of burgers, some of the additional items (additions) to
// be added to the burgers and pricing.
// We want to create a base hamburger, but also two other types of hamburgers that are popular ones in
// Bills store.
// The basic hamburger should have the following items:
// bread roll type, meat and up to 4 additional additions (things like lettuce, tomato, carrot, etc) that
// the customer can select to be added to the burger. Each one of these items gets charged an additional
// price so you need some way to track how many items got added and to calculate the price (for the base
// burger and all the additions).
// This burger has a base price and the additions are all separately priced.
// Create a Hamburger class to deal with all the above.
// The constructor should only include the roll type, meat and price.
// Also create two extra varieties of Hamburgers (classes) to cater for:
//    a) Healthy burger (on a brown rye bread roll), plus two addition items can be added.
//       The healthy burger can have a total of 6 items (Additions) in total.
//       Hint: you probably want to process the 2 additional items in this new class, not the base class,
//       since the 2 additions are only appropriate for this new class.
//    b) Deluxe hamburger - comes with chips and drinks as additions, but no extra additions are allowed.
//       Hint:  You have to find a way to automatically add these new additions at the time the deluxe burger
//       object is created, and then prevent other additions being made.
// All 3 classes should have a method that can be called anytime to show the base price of the hamburger
// plus all additional, each showing the addition name, and addition price, and a grand total for the
// burger.
// For the two additional classes this may require you to be looking at the base class for pricing and then
// adding totals onto that.

public class Hamburger {
    private String breadRollType;
    private String meat;
    private int toppingsCount;
    protected int maxToppings;
    private StringBuffer toppings;
    private double basePrice;
    private double toppingPrice;

    public Hamburger(String breadRollType, String meat, double basePrice, double toppingPrice) {
        this.breadRollType = breadRollType;
        this.meat = meat;
        this.maxToppings = 4;
        this.basePrice = basePrice;
        this.toppingPrice = toppingPrice;
        toppings = new StringBuffer(); // TODO: Is this needed?
    }

    public boolean addTopping(String topping) {
        boolean isValid = toppingsCount < maxToppings;
        if (toppingsCount < maxToppings) {
            toppings.append(topping).append(" ");
            toppingsCount++;
        } else {
            // Will automatically display the name of the most derived class.
            System.out.println("Too many toppings in " + getClass().getSimpleName() + ".");
        }
        return isValid;
    }

    public String getBreadRollType() {
        return breadRollType;
    }

    public String getMeat() {
        return meat;
    }

    public int getToppingsCount() {
        return toppingsCount;
    }

    public StringBuffer getToppings() {
        return toppings;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getToppingPrice() {
        return toppingPrice;
    }

    public double getTotalPrice() {
        return basePrice + toppingPrice*toppingsCount;
    }
}
