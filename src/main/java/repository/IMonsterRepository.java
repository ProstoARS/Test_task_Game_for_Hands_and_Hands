package repository;

import model.Monster;

import java.util.List;

public interface IMonsterRepository {

    Monster addMonster(Monster monster);

    Monster findById(int id);

    List<Monster> findAll();
}
