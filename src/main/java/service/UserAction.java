package service;

import io.Input;
import repository.IGamerRepository;

public interface UserAction {
    String name();

    int execute(Input input, IGamerRepository repository);
}
