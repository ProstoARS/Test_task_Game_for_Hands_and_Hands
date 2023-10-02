package repository;

import model.Damage;
import model.Monster;

import java.util.ArrayList;
import java.util.List;

public class MemoryMonsterRepository {

    private final List<Monster> monsters = new ArrayList<>();
    private int id = 1;

    public MemoryMonsterRepository() {
        addMonster(new Monster("Троль", 15, 20, 20, new Damage(6, 11)));
        addMonster(new Monster("Виверна", 20, 15, 15, new Damage(3, 9)));
        addMonster(new Monster("Вурдалак", 25, 10, 23, new Damage(8, 12)));
    }

    public Monster addMonster(Monster monster) {
        monster.setId(id++);
        monsters.add(monster);
        return monster;
    }

    public Monster findById(int id) {
        return monsters.get(id);
    }

    public List<Monster> findAll() {
        return List.copyOf(this.monsters);
    }
}
