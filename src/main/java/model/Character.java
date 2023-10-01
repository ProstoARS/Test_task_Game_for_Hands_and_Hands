package model;

import exceptions.CharacterDeathException;

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

    public boolean checkDeathCharacter(int health) {
        return health <= 0;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getProtection() {
        return protection;
    }

    public Damage getDamage() {
        return damage;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) throws CharacterDeathException {
        if (checkDeathCharacter(health)) {
            throw new CharacterDeathException("Персонаж умер");
        }
        this.health = health;
    }
}
