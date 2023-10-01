package model;

import exceptions.CharacterDeathException;

public class Gamer extends Character {

    private final int maxHealth;

    public Gamer(String name, int attack, int protection, int health, Damage damage) {
        super(name, attack, protection, health, damage);
        this.maxHealth = health;
    }

    public int recoveryHealth() throws CharacterDeathException {
        double recoveryValue = maxHealth * 0.3;
        int newHeath = super.getHealth() + (int) Math.round(recoveryValue);
        if (newHeath > maxHealth) {
            newHeath = maxHealth;
        }
        super.setHealth(newHeath);
        return newHeath;
    }

}
