package com.cjm.ms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
        this.exits = new HashMap<>();
        this.exits.put("Q", 0);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        // Prevents outside classes from altering the map within this object.
        return new HashMap<>(exits);
    }

    public void addExit(String direction, int location) {
        exits.put(direction, location);
    }
}

public class Adventure {
    private final Map<String, String> vocabulary;
    private Map<Integer, Location> locations = new HashMap<>();

    public Adventure() {        
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java."));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building."));
        locations.put(2, new Location(2, "You are at the top of a hill."));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring."));
        locations.put(4, new Location(4, "You are in a valley beside a stream."));
        locations.put(5, new Location(5, "You are in the forest."));

        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);

        start();
    }

    // Exercise:
    // Change the program to allow players to type full words, or phrases, then move to the
    // correct location based upon their input.
    // The player should be able to type commands such as "Go West", "run South", or just "East"
    // and the program will move to the appropriate location if there is one.  As at present, an
    // attempt to move in an invalid direction should print a message and remain in the same place.
    //
    // Single letter commands (N, W, S, E, Q) should still be available.
    public void start() {
        Scanner sc = new Scanner(System.in);
        int currLoc = 1;
        while (true) {
            System.out.println(locations.get(currLoc).getDescription());
            if (currLoc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(currLoc).getExits();
            System.out.print("Available exits are: ");
            for (String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.print(": ");

            String dir = parseInput(sc.nextLine());
            if (exits.containsKey(dir)) {
                currLoc = exits.get(dir);
            } else {
                System.out.println("You cannot go in that direction.\n");
            }
        }
    }

    public String parseInput(String input) {
        input = input.trim().toUpperCase();
        String[] words = input.split(" ");
        for (String word: words) {
            switch (word) {
                case "NORTH": case "N":
                    return "N";
                    break;
                case "EAST":  case "E":
                    return "E";
                    break;
                case "SOUTH": case "S":
                    return "S";
                    break;
                case "WEST":  case "W":
                    return "W";
                    break;
                case "QUIT":  case "Q":
                    return "Q";
                    break;
            }
        }
    }
}
