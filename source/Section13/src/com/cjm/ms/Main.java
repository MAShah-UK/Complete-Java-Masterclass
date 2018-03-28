package com.cjm.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        createTheatre();
    }

    // Practice using Collections.swap.
    public void bubbleSort(List<? extends Theatre.Seat> list) {
        boolean changed = true;
        while (changed) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i+1).compareTo(list.get(i)) > 0) {
                    Collections.swap(list, i, i+1);
                }
            }
        }
    }

    // Practice working with collections and comparator.
    public static void createTheatre() {
        System.out.println("BEGIN: createTheatre");

        Theatre theatre = new Theatre("Olympian", 8, 12);
        if (theatre.reserveSeat("H11")) {
            System.out.println("Please pay for H11.");
        } else {
            System.out.println("Seat already reserved.");
        }
        if (theatre.reserveSeat("B13")) {
            System.out.println("Please pay for B13.");
        } else {
            System.out.println("Seat already reserved.");
        }
        if (theatre.reserveSeat("B13")) {
            System.out.println("Please pay for B13.");
        } else {
            System.out.println("Seat already reserved.");
        }

//        // Creates a shallow copy: references are copied over and so they point to the same object.
//        List<Theatre.Seat> seatsCopy = new ArrayList<>(theatre.seats);
//        Theatre.printSeats(seatsCopy);
//        seatsCopy.get(1).reserve();
//        if (theatre.reserveSeat("A02")) {
//            System.out.println("Please pay for A02.");
//        } else {
//            System.out.println("Seat is already reserved.");
//        }
//
//        // Puts seats in random order.
//        Collections.shuffle(seatsCopy);
//        System.out.println("Printing seat copy:");
//        Theatre.printSeats(seatsCopy);
//        System.out.println("Printing theatre.seat:");
//        Theatre.printSeats(theatre.seats);
//
//        Theatre.Seat minSeat = Collections.min(seatsCopy);
//        Theatre.Seat maxSeat = Collections.max(seatsCopy);
//        System.out.println("Min seat number is " + minSeat.getSeatNumber() + ".");
//        System.out.println("Max seat number is " + maxSeat.getSeatNumber() + ".");
//
//        // Won't work since the capacity is set to size(), but it doesn't actually have any elements.
//        List<Theatre.Seat> list = new ArrayList<>(theatre.seats.size());
//        Collections.copy(list, theatre.seats);

        List<Theatre.Seat> reverseSeats = new ArrayList<>(theatre.getSeats());
        Collections.reverse(reverseSeats);
        Theatre.printSeats(reverseSeats);

        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);
        Theatre.printSeats(priceSeats);

    }
}
