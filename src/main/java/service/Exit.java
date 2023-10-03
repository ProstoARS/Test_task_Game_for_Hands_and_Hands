package service;

import io.Input;
import io.Output;
import repository.IGamerRepository;

public class Exit implements UserAction {
    private final Output out;

    public Exit(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public int execute(Input input, IGamerRepository repository) {
        out.println("==== Exit Program ====");
        return -1;
    }
}
