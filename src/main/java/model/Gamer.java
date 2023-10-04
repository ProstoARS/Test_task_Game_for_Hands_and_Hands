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
        int oldHeath = getHealth();
        int recoveryValue = (int) Math.round(maxHealth * 0.3);
        int newHeath = super.getHealth() + recoveryValue;
        if (newHeath > maxHealth) {
            newHeath = maxHealth;
            recoveryValue = newHeath - oldHeath;
        }
        super.setHealth(newHeath);
        return recoveryValue;
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
