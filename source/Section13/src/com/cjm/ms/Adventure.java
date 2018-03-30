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
    private Map<Integer, Location> locations = new HashMap<>();

    public Adventure() {
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java."));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building."));
        locations.put(2, new Location(2, "You are at the top of a hill."));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring."));
        locations.put(4, new Location(4, "You are in a valley beside a stream."));
        locations.put(5, new Location(5, "You are in the forest."));
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int currLoc = 1;
        while (true) {
            System.out.println(locations.get(currLoc).getDescription());
            if (currLoc == 0) {
                break;
            }
            currLoc = sc.nextInt();
            if (!locations.containsKey(currLoc)) {
                System.out.println("You cannot go in that direction.");
            }
        }
    }
}
