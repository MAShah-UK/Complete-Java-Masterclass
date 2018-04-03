package com.cjm.ms;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        // To make a class immutable avoid setters and copy arguments rather than directly setting them.
        // The calling class may still have a reference to exits which would allow changing it after the fact.
        this.exits = exits == null ? new HashMap<>() : new HashMap<>(exits);
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
}

public class Adventure {
    private Map<Integer, Location> locations = new HashMap<>();

    public Adventure() {
        Map<String, Integer> tmpExit = new HashMap<>();
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java.", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("W", 2);
        tmpExit.put("E", 3);
        tmpExit.put("S", 4);
        tmpExit.put("N", 5);
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building.", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("N", 5);
        locations.put(2, new Location(2, "You are at the top of a hill.", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("W", 1);
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring.", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("N", 1);
        tmpExit.put("W", 2);
        locations.put(4, new Location(4, "You are in a valley beside a stream.", tmpExit));

        tmpExit = new HashMap<>();
        tmpExit.put("S", 1);
        tmpExit.put("W", 2);
        locations.put(5, new Location(5, "You are in the forest.", tmpExit));

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
        String output = "";
        String[] words = input.trim().toUpperCase().split(" ");
        for (String word: words) {
            switch (word) {
                case "NORTH": case "N":
                    output = "N";
                    break;
                case "EAST":  case "E":
                    output = "E";
                    break;
                case "SOUTH": case "S":
                    output = "S";
                    break;
                case "WEST":  case "W":
                    output = "W";
                    break;
                case "QUIT":  case "Q":
                    output = "Q";
                    break;
            }
            if (!output.isEmpty()) {
                break;
            }
        }
        return output;
    }
}
