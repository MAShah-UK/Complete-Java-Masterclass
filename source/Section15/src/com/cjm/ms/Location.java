package com.cjm.ms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dev on 8/12/2015.
 */
// Serialization allows saving objects to a file.
public class Location implements Serializable {
    // Runtime uses this to determine if the saved data is compatible with executable.
    private long serialVersionUID = 1L;

    // All fields are serializable.
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exits = new LinkedHashMap<>(exits);
        } else {
            this.exits = new LinkedHashMap<>();
        }
        this.exits.put("Q", 0);
    }

//    public void addExit(String direction, int location) {
//        exits.put(direction, location);
//    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new LinkedHashMap<>(exits);
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }
}
