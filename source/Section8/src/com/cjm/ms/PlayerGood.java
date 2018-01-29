package com.cjm.ms;

public class PlayerGood {
    // Will allow setting fields directly. Not recommended!
    private String name;
    private int hitPoints = 100; // We can change the field's name with no repercussion, due to getters and setters.
    private String weapon;

    // We can gaurantee that fields are initialised as required since they're private.
    public PlayerGood(String name, int health, String weapon) {
        this.name = name;

        // Health validation.
        if (health > 0 && health <= 100) {
            this.health = health;
        }

        this.weapon = weapon;
    }

    public void loseHealth(int damage) {
        hitPoints -= damage;

        if (hitPoints <= 0) {
            System.out.println("Player knocked out.");
            // Reduce player lives.
        }
    }

    public int getHealth() {
        return hitPoints;
    }
}
