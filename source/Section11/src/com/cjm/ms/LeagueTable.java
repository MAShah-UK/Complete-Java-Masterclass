package com.cjm.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Exercise:
// Create a generic class to implement a league table for a sport.
// The class should allow teams to be added to the list, and store
// a list of teams that belong to the league.
//
// Your class should have a method to print out the teams in order,
// with the team at the top of the league printed first.
//
// Only teams of the same type should be added to any particular
// instance of the league class - the program should fail to compile
// if an attempt is made to add an incompatible team.

public class LeagueTable<T extends Team> {
    private String name;
    private List<T> teams = new ArrayList<>();

    public LeagueTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTeams(T ... newTeams) {
        for (T team : newTeams) {
            String output;
            if (!teams.contains(team)) {
                teams.add(team);
                output = "Team " + team.getName() + " added to " + name;
            } else {
                output = "Team " + team.getName() + " already exists in " + name;
            }
            System.out.println(output);
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Collections.sort(teams);
        int i = 1;
        for (T team : teams) {
            output.append(i).append(") ").append(team.getName()).append(": ").append(team.ranking()).append(" points. \n");
            i++;
        }
        return output.toString();
    }
}
