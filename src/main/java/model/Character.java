package model;

public abstract class Character {

    private final String name;

    private final int attack;

    private final int protection;

    private final int health;

    private final Damage damage;

    public Character(String name, int attack, int protection, int health, Damage damage) {
        this.name = name;
        this.attack = attack;
        this.protection = protection;
        this.health = health;
        this.damage = damage;
    }
}
