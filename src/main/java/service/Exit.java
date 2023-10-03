package service;

import io.Input;
import io.Output;
import repository.IGamerRepository;
import repository.IMonsterRepository;

public class Exit implements UserAction {
    private final Output out;

    public Exit(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Завершить Игру";
    }

    @Override
    public int execute(Input input, IGamerRepository repository, IMonsterRepository monsterRepository) {
        return -1;
    }
}
