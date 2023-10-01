package model;

public abstract class Character {

    private final String name;

    private final int attack;

    private final int protection;

    private int health;

    private final Damage damage;

    public Character(String name, int attack, int protection, int health, Damage damage) {
        this.name = name;
        this.attack = attack;
        this.protection = protection;
        this.health = health;
        this.damage = damage;
    }

    public boolean checkDeathCharacter() {
        return this.health <= 0;
    }

    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
