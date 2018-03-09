package com.cjm.ms;

import java.util.ArrayList;

// The <T> means that the class requires a type parameter argument  that will be stored as T.
// class Team<T> {
// Bounded type parameter that only allows subclasses of Player to be specified.
class Team<T extends Player> {
    private String name;
    private int played;
    private int won;
    private int lost;
    private int tied;

    // This is bad code since it allows members of any sport to join the same team.
    // This is nonsensical as a team usually consists of members of the same sport.
    // private ArrayList<Player> members = new ArrayList<>();
    private ArrayList<T> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(T player) {
        if (members.contains(player)) {
            // For class Team<T>:
            // Error since T can be any type so the compiler doesn't know if it contains getName().
            // System.out.println(player.getName() + " is already on the team.");
            // Works since the compiler knows that Player contains it, but is bad code quality.
            // System.out.println( ((Player) player).getName() + " is already on the team.");

            // For class Team<T extends Player>:
            System.out.println(player.getName() + " is already on the team.");
        } else {
            members.add(player);
            System.out.println(player.getName() + " picked for team " + name);
        }
    }

    public int numPlayers() {
        return members.size();
    }

    public void matchResult(Team opponent, int ourScore, int theirScore) {
        if (ourScore > theirScore) {
            won++;
        } else if (ourScore < theirScore) {
            lost++;
        } else {
            tied++;
        }
        played++;
        if (opponent != null) { // Prevents infinite loop.
            opponent.matchResult(null, theirScore, ourScore);
        }
    }

    public int ranking() {
        return (won * 2) + tied;
    }
}