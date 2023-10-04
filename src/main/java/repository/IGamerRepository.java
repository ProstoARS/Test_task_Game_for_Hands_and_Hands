package repository;

import model.Gamer;

import java.util.List;

public interface IGamerRepository {

    void init();

    Gamer addGamer(Gamer gamer);

    Gamer findById(int id);

    List<Gamer> findAll();
}
