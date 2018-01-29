package com.cjm.ms;

public class Bed {
    private int maxPpl;
    private Dimensions dim;

    public Bed(int maxPpl, Dimensions dim) {
        this.maxPpl = maxPpl;
        this.dim = dim;
    }

    public void sleep(int numOfPpl) {
        if (numOfPpl > maxPpl) {
            System.out.println("Too many people sleeping on the bed!");
        } else {
            System.out.println("Everyone is sleeping on the bed.");
        }
    }
}
