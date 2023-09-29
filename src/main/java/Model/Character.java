package Model;

public abstract class Character {

    private String name;

    private int attack;

    private int protection;

    private int health;

    private Damage damage;

    public Character(String name, int attack, int protection, int health, Damage damage) {
        this.name = name;
        this.attack = attack;
        this.protection = protection;
        this.health = health;
        this.damage = damage;
    }
}
