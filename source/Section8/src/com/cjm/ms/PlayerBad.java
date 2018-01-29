package com.cjm.ms;

public class PlayerBad {

    // Will allow setting fields directly. Not recommended!
    public String name;
    public int health;
    public String weapon;

    public void loseHealth(int damage) {
        health -= damage;

        if (health <= 0) {
            System.out.println("Player knocked out.");
            // Reduce player lives.
        }
    }

    public int healthRemaining() {
        return health;
    }
}
