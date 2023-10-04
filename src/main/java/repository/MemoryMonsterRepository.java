package repository;

import model.Damage;
import model.Monster;

import java.util.ArrayList;
import java.util.List;

public class MemoryMonsterRepository implements IMonsterRepository {

    private final List<Monster> monsters = new ArrayList<>();
    private int id = 1;

    public MemoryMonsterRepository() {
        addMonster(new Monster("Троль", 15, 20, 20, new Damage(1, 8)));
        addMonster(new Monster("Виверна", 20, 15, 15, new Damage(2, 6)));
        addMonster(new Monster("Вурдалак", 25, 10, 23, new Damage(6, 10)));
    }

    @Override
    public Monster addMonster(Monster monster) {
        monster.setId(id++);
        monsters.add(monster);
        return monster;
    }

    @Override
    public Monster findById(int id) {
        return monsters.get(id);
    }

    @Override
    public List<Monster> findAll() {
        return List.copyOf(this.monsters);
    }
}
