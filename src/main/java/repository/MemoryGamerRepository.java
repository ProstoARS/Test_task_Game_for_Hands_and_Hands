package repository;

import model.Damage;
import model.Gamer;

import java.util.ArrayList;
import java.util.List;

public class MemoryGamerRepository {

    private final List<Gamer> gamers = new ArrayList<>();
    private int id = 1;

    public MemoryGamerRepository() {
        addGamer(new Gamer("Рыцарь", 15, 25, 30, new Damage(3, 9)));
        addGamer(new Gamer("Варвар", 20, 18, 30, new Damage(5, 12)));
        addGamer(new Gamer("Ниндзя", 30, 10, 20, new Damage(15, 20)));
    }

    public Gamer addGamer(Gamer gamer) {
        gamer.setId(id++);
        gamers.add(gamer);
        return gamer;
    }

    public Gamer findById(int id) {
        return gamers.get(id);
    }

    public List<Gamer> findAll() {
        return List.copyOf(this.gamers);
    }
}
