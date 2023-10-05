package repository;

import model.Damage;
import model.Gamer;

import java.util.ArrayList;
import java.util.List;

public class MemoryGamerRepository implements IGamerRepository {

    private List<Gamer> gamers;
    private int id;

    @Override
    public void init() {
        this.id = 1;
        this.gamers = new ArrayList<>();
        addGamer(new Gamer("Рыцарь", 18, 25, 30, new Damage(3, 9)));
        addGamer(new Gamer("Варвар", 22, 20, 30, new Damage(5, 12)));
        addGamer(new Gamer("Ниндзя", 30, 17, 20, new Damage(15, 20)));
    }

    @Override
    public Gamer addGamer(Gamer gamer) {
        gamer.setId(id++);
        gamers.add(gamer);
        return gamer;
    }

    @Override
    public Gamer findById(int id) {
        return gamers.get(id);
    }

    @Override
    public List<Gamer> findAll() {
        return List.copyOf(this.gamers);
    }
}
