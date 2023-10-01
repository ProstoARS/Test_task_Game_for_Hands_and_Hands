package model;

public class Gamer extends Character {

    private final int maxHealth;

    public Gamer(String name, int attack, int protection, int health, Damage damage) {
        super(name, attack, protection, health, damage);
        this.maxHealth = health;
    }

    public int recoveryHealth() {
        double recoveryValue = maxHealth * 0.3;
        int newHeath = super.getHealth() + (int) Math.round(recoveryValue);
        super.setHealth(newHeath);
        return newHeath;
    }

}
