package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        createTheatre();
    }

    // Practice
    public static void createTheatre() {
        System.out.println("BEGIN: createTheatre");

        Theatre theatre = new Theatre("Olympian", 8, 12);
        theatre.getSeats();
        if (theatre.reserveSeat("H11")) {
            System.out.println("Please pay.");
        } else {
            System.out.println("Sorry, seat is taken.");
        }
    }
}
