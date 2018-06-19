package com.cjm.ms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    // The static initializer block is the first code block to run when the class is loaded.
    static {
        Map<String, Integer> tempExit;
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java.", null));

        tempExit = new HashMap<>();
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building.", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 5);
        locations.put(2, new Location(2, "You are at the top of a hill.", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 1);
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring.", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        locations.put(4, new Location(4, "You are in a valley beside a stream.", tempExit));

        tempExit = new HashMap<>();
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        locations.put(5, new Location(5, "You are in the forest.", tempExit));
    }

    public static void main(String[] args) throws IOException {
//        // Original.
//        FileWriter locFile = null;
//        try {
//            // Throws a checked exception which must be handled to avoid compilation errors.
//            // Will throw an IOException if 'locations.txt' exists as a directory.
//            locFile = new FileWriter("locations.txt");
//            for(Location location: locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//            }
//            // Bad idea to close resources in try block. Might not run if exception is thrown.
//            // locFile.close();
//        } catch(IOException e) {
//            System.out.println("In catch block.");
//            e.printStackTrace();
//        } finally {
//            // Can have try-catch blocks within try-catch blocks.
//            try {
//                if (locFile != null) {
//                    System.out.println("Attempting to close locFile.");
//                    locFile.close();
//                }
//            } catch(IOException e) {
//                e.printStackTrace();
//            }
//        }

//        // After propagating IOException.
//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for(Location location: locations.values()) {
//                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//            }
//        } finally {
//            if (locFile != null) {
//                System.out.println("Attempting to close locFile.");
//                locFile.close();
//            }
//        }

        // After using try-with-resources statement.
        // After propagating IOException, and using try-with-resources statement.
        // Classes must implement AutoCloseable to do this.
        // Multiple resources can be listed, use semicolons on all but the last.
        // Java automatically calls the close method on each resource to release it.
        try(FileWriter locFile = new FileWriter("locations.txt")) {
            for(Location location: locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            }
        }
    }

    // Override methods by redirecting them to the HashMap implementation.

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
