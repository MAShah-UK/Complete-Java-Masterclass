package com.cjm.ms;

import java.util.*;
import com.cjm.ms.HeavenlyBody.BodyTypes;

public class Main {

    public static void main(String[] args) {
        createTheatre();
        createMap();
        //createAdventure();
        createHeavenlyBodies();
        findSquaresCubes();
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

    // Practice working with sets via exercise.
    public static void createHeavenlyBodies() {
        System.out.println("\nBEGIN: createHeavenlyBodies");

        Map<String, HeavenlyBody> solarSystem = new HashMap<>();
        Set<HeavenlyBody> planets = new HashSet<>();

        HeavenlyBody tmp = new PlanetBody("Mercury", 88);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmp = new PlanetBody("Venus", 225);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmp = new PlanetBody("Earth", 365);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        HeavenlyBody tmpMoon = new MoonBody("Moon", 27);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon);

        tmp = new PlanetBody("Mars", 687);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmpMoon = new MoonBody("Deimos", 1.3);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon); // tmp is still Mars

        tmpMoon = new MoonBody("Phobos", 0.3);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon); // tmp is still Mars

        tmp = new PlanetBody("Jupiter", 4332);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmpMoon = new MoonBody("Io", 1.8);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon); // tmp is still Jupiter

        tmpMoon = new MoonBody("Europa", 3.5);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon); // tmp is still Jupiter

        tmpMoon = new MoonBody("Ganymede", 7.1);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon); // tmp is still Jupiter

        tmpMoon = new MoonBody("Callisto", 16.7);
        solarSystem.put(tmpMoon.getKey(), tmpMoon);
        tmp.addSatellite(tmpMoon); // tmp is still Jupiter

        tmp = new PlanetBody("Saturn", 10759);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmp = new PlanetBody("Uranus", 30660);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmp = new PlanetBody("Neptune", 165);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        tmp = new PlanetBody("Pluto", 248);
        solarSystem.put(tmp.getKey(), tmp);
        planets.add(tmp);

        System.out.print("Planets: ");
        for (HeavenlyBody planet: planets) {
            System.out.print(planet.getName() + " ");
        }
        System.out.println();

        HeavenlyBody body = solarSystem.get(HeavenlyBody.makeKey("Jupiter", BodyTypes.PLANET));
        System.out.print("Moons of " + body.getName() + ": ");
        for (HeavenlyBody jupiterMoon: body.getSatellites()) {
            System.out.print(jupiterMoon.getName() + " ");
        }
        System.out.println();

        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody planet: planets) {
            // Creates a union between sets. Duplicates will be ignored.
            moons.addAll(planet.getSatellites());
        }

        System.out.print("All moons: ");
        for (HeavenlyBody moon : moons) {
            System.out.print(moon.getName() + " ");
        }
        System.out.println();

//        // Sets can only contain unique items, but because HeavenlyBody doesn't override Object.equals() to compare
//        // the body's name Set has no way of knowing that the new HeavenlyBody already exists.
//        HeavenlyBody pluto = new HeavenlyBody("Pluto", 842);
//        planets.add(pluto);
//        System.out.println("Planets:");
//        for (HeavenlyBody planet: planets) {
//            System.out.println("\t" + planet.getName() + ": " + planet.getOrbitalPeriod());
//        }
    }

    // Practice working with sets.
    public static void findSquaresCubes() {
        System.out.println("\nBEGIN: findSquaresCubes");

        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        for (int i = 1; i < 100; i++) {
            squares.add((int)Math.pow(i, 2));
            cubes.add((int)Math.pow(i, 3));
        }

        // Combine sets.
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("Union contains: " + union.size() + " elements.");

        // Find common elements in sets.
        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);
        System.out.println("Intersection contains: " + intersection.size() + " elements.");
        for (int i: intersection) {
            System.out.println(Math.cbrt(i) + " squared is " + Math.sqrt(i) + " and cubed is " + i + ".");
        }

        // String to set.
        Set<String> words = new HashSet<>();
        String sentence = "One day in the year of the fox.";
        String[] wordsArr = sentence.split(" ");
        words.addAll(Arrays.asList(wordsArr));
        for (String s: words) {
            // Sets don't print in any particular order.
            System.out.print(s + " ");
        }
        System.out.println();

        // Asymmetric difference between sets.
        Set<String> nature = new HashSet<>();
        String[] natureWords = {"all", "nature", "is", "but", "art", "unknown", "to", "thee"};
        nature.addAll(Arrays.asList(natureWords));

        Set<String> divine = new HashSet<>();
        String[] divineWords = {"to", "err", "is", "Human", "to", "forgive", "divine"};
        divine.addAll(Arrays.asList(divineWords));

        System.out.print("Nature - Divine: ");
        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine);
        for (String word: diff1) {
            System.out.print(word + " ");
        }
        System.out.println();

        System.out.print("Divine - Nature: ");
        Set<String> diff2 = new HashSet<>(divine);
        diff2.removeAll(nature);
        for (String word: diff2) {
            System.out.print(word + " ");
        }
        System.out.println();

        // Symmetric difference between sets.
        System.out.print("Symmetric difference: ");
        Set<String> unionTest = new HashSet<>(nature);
        unionTest.addAll(divine);
        Set<String> intersectionTest = new HashSet<>(nature);
        intersectionTest.retainAll(divine);
        unionTest.removeAll(intersectionTest);
        for (String word: unionTest) {
            System.out.print(word + " ");
        }
        System.out.println();

        // Determine if set is a superset/subset.
        if (nature.containsAll(divine)) {
            System.out.println("Divine is a subset of nature.");
        }
        if (nature.containsAll(intersectionTest)) {
            System.out.println("Intersection is a subset of nature.");
        }
        if (divine.containsAll(intersectionTest)) {
            System.out.println("Intersection is a subset of divine.");
        }
    }
}
