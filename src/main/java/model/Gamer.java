package model;

import exceptions.CharacterDeathException;

import java.util.Objects;

public class Gamer extends Character {

    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gamer gamer)) {
            return false;
        }
        return id == gamer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
