package com.cjm.ms;

// Exercise:
// Create a class and demonstate proper encapsulation techniques.
// The class will be called Printer.
// It will simulate a real computer printer.
// It should have fields for the toner Level, number of pages printed, and
// also whether its a duplex printer (capable of printing on both sides of the paper).
// Add methods to fill up the toner (up to a maximum of 100%), another method to
// simulate printing a page (which should increase the number of pages printed).
// Decide on the scope, whether to use constructors, and anything else you think is needed.

public class Printer {
    private double tonerLvl = 100.0;
    private double tonerPerPage = 0.5;
    private int pagesPrinted;
    private boolean isDuplex;

    public Printer() {

    }

    public Printer(double tonerLvl, int pagesPrinted, boolean isDuplex) {
        this.tonerLvl = tonerLvl;
        this.pagesPrinted = pagesPrinted;
        this.isDuplex = isDuplex;
    }

    public boolean fillToner(double amount) {
        double newTonerLvl = tonerLvl + amount;
        boolean isValid = (amount > 0) &&
                          (newTonerLvl > 0 && newTonerLvl <= 100);
        if (isValid) {
            tonerLvl = newTonerLvl;
            System.out.println("Toner filled to " + tonerLvl + "%.");
        } else {
            System.out.println("Toner unchanged.");
        }

        return isValid;
    }

    public boolean print(int pages, boolean isDoubleSided) {
        double newTonerLvl = tonerLvl - tonerPerPage*pages;
        boolean isValid = newTonerLvl > 0;

        if (isValid) {
            pagesPrinted += pages;
            tonerLvl = newTonerLvl;
            System.out.println(pages + " pages successfully printed. Toner level is " + tonerLvl + ".");
        } else {
            System.out.println("Error: Not enough toner left to print.");
        }

        return isValid;
    }

    public double getTonerLvl() {
        return tonerLvl;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }

    public boolean isDuplex() {
        return isDuplex;
    }
}
