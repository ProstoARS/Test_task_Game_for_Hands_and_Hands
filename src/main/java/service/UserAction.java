package service;

import io.Input;
import repository.IGamerRepository;
import repository.IMonsterRepository;

public interface UserAction {
    String name();

    int execute(Input input, IGamerRepository repository, IMonsterRepository monsterRepository);
}
