package com.cjm.ms;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        createTheatre();
        createMap();
        //createAdventure();
        createHeavenlyBodies();
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

    // Practice working with maps.
    public static void createMap() {
        System.out.println("\nBEGIN: createMap");

        Map<String, String> languages = new HashMap<>();
        // Use put() to add a key-value pair to the map.
        // put() returns the last value associated with that key, in these cases it will be null.
        System.out.println("Previous value for Java: " + languages.put("Java", "A compiled high level, object-oriented, platform independent language."));
        languages.put("Python", "An interpreted, object-orientated, high-level programming language with dynamic semantics.");
        languages.put("Algol", "An algorithmic language.");
        languages.put("BASIC", "Beginners All Purposes Symbolic Instruction Code.");
        languages.put("Lisp", "Therein lies madness.");

        // Use get() to retrieve the value associated with a given key.
        System.out.println(languages.get("Java"));
        // If you enter a pre-existing key its value is overwritten.
        // put() will return the previous value associated with "Java".
        System.out.println("Previous value for Java: " + languages.put("Java", "This course is about Java."));

        // Prevent accidental overwrites by checking if a key exists.
        if (languages.containsKey("Java")) {
            System.out.println("Java is already in the map.");
        } else {
            languages.put("Java", "This course is about Java.");
        }

        // Removes a key-value pair based on key and value. Will return true if key-value pair was removed.
        languages.remove("Lisp", "Another language.");
        // Replaces a value for a given key.
        languages.replace("Lisp", "Value replaced with a new value.");
        // Removes a key-value pair based on key only.
        languages.remove("Lisp");
        System.out.println("\nLoop through the map: ");
        for (String key : languages.keySet()) {
            System.out.println("[" + key + "] " + languages.get(key));
        }
    }

    // Practice working with maps via exercise.
    public static void createAdventure() {
        System.out.println("\nBEGIN: createAdventure");

        new Adventure();
    }

    // Practice working with sets.
    public static void createHeavenlyBodies() {
        System.out.println("\nBEGIN: createAdventure");

        Map<String, HeavenlyBody> solarSystem = new HashMap<>();
        Set<HeavenlyBody> planets = new HashSet<>();

        HeavenlyBody tmp = new HeavenlyBody("Mercury", 88);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmp = new HeavenlyBody("Venus", 225);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmp = new HeavenlyBody("Earth", 365);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        HeavenlyBody tmpMoon = new HeavenlyBody("Moon", 27);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon);

        tmp = new HeavenlyBody("Mars", 687);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmpMoon = new HeavenlyBody("Deimos", 1.3);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon); // tmp is still Mars

        tmpMoon = new HeavenlyBody("Phobos", 0.3);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon); // tmp is still Mars

        tmp = new HeavenlyBody("Jupiter", 4332);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmpMoon = new HeavenlyBody("Io", 1.8);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon); // tmp is still Jupiter

        tmpMoon = new HeavenlyBody("Europa", 3.5);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon); // tmp is still Jupiter

        tmpMoon = new HeavenlyBody("Ganymede", 7.1);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon); // tmp is still Jupiter

        tmpMoon = new HeavenlyBody("Callisto", 16.7);
        solarSystem.put(tmpMoon.getName(), tmpMoon);
        tmp.addMoon(tmpMoon); // tmp is still Jupiter

        tmp = new HeavenlyBody("Saturn", 10759);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmp = new HeavenlyBody("Uranus", 30660);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmp = new HeavenlyBody("Neptune", 165);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        tmp = new HeavenlyBody("Pluto", 248);
        solarSystem.put(tmp.getName(), tmp);
        planets.add(tmp);

        System.out.println("Planets");
        for (HeavenlyBody planet: planets) {
            System.out.println("\t" + planet.getName());
        }

        HeavenlyBody body = solarSystem.get("Jupiter");
        System.out.println("Moons of " + body.getName());
        for (HeavenlyBody jupiterMoon: body.getSatellites()) {
            System.out.println("\t" + jupiterMoon.getName());
        }

        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody planet: planets) {
            // Creates a union between sets. Duplicates will be ignored.
            moons.addAll(planet.getSatellites());
        }

        System.out.println("All Moons");
        for (HeavenlyBody moon : moons) {
            System.out.println("\t" + moon.getName());
        }
    }
}
