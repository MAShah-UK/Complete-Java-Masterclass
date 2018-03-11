package com.cjm.ms;

import java.util.ArrayList;

// The <T> means that the class requires a type parameter argument  that will be stored as T.
// class Team<T> {
// Bounded type parameter that only allows subclasses of Player to be specified.
class Team<T extends Player> implements Comparable<Team<T>> {
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
        this(name, 0, 0, 0);
    }

    public Team(String name, int initialWon, int initialLost, int initialTied) {
        this.name = name;
        this.won = initialWon;
        this.lost = initialLost;
        this.tied = initialTied;
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

    // The problem with this is that a team for one sport can be compared with a team for another sport.
    // You only want teams within the same sport to be considered.
    // public void matchResult(Team opponent, int ourScore, int theirScore) {
    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        String message;
        if (ourScore > theirScore) {
            won++;
            message = " beat ";
        } else if (ourScore < theirScore) {
            lost++;
            message = " drew with ";
        } else {
            tied++;
            message = " lost to ";
        }
        played++;
        if (opponent != null) { // Prevents infinite loop.
            System.out.println(getName() + message + opponent.getName());
            opponent.matchResult(null, theirScore, ourScore);
        }
    }

    public int ranking() {
        return (won * 2) + tied;
    }

    // Used by List.contains() to determine if an equivalent element already exists.
    @Override
    public boolean equals(Object team) {
        if (team instanceof Team) {
            return getName().equals( ((Team) team).getName() );
        }
        return false;
    }

    // Used by Collections.sort() to sort elements in a specific order.
    @Override
    public int compareTo(Team<T> team) {
        if (ranking() > team.ranking()) {
            return -1;
        } else if (ranking() < team.ranking()) {
            return 1;
        } else {
            return 0;
        }
    }
}