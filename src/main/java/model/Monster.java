package model;


import java.util.Objects;

public class Monster extends Character {

    private int id;

    public Monster(String name, int attack, int protection, int health, Damage damage) {
        super(name, attack, protection, health, damage);
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
        if (!(o instanceof Monster monster)) {
            return false;
        }
        return id == monster.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
