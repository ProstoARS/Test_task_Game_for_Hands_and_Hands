import io.*;
import model.Gamer;
import model.Monster;
import repository.IGamerRepository;
import repository.IMonsterRepository;
import repository.MemoryGamerRepository;
import repository.MemoryMonsterRepository;
import service.CharacterSelection;
import service.Exit;
import service.StartGame;
import service.UserAction;

import java.util.Arrays;
import java.util.List;

public class GameRunner {

    private final Output out;

    public GameRunner(Output out) {
        this.out = out;
    }

    public void init(Input input,
                     IGamerRepository gamerRepository,
                     IMonsterRepository monsterRepository,
                     List<UserAction> actions) {
        int run = 0;
        while (run >= 0) {
            this.showMenu(actions);
            int select = input.askInt("Выберите: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Неправильный ввод, выберите: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, gamerRepository, monsterRepository);
            if (run == -1) {
                continue;
            }
            Gamer gamer = gamerRepository.findById(run);
            List<Monster> monsterList = monsterRepository.findAll();
            try {
                run = new StartGame(input, out, gamer, monsterList).execute();
            } catch (InterruptedException e) {
                out.println("Игра зависла");
            }
        }
        out.println("----- Игра закончилась -----");
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu.");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        IGamerRepository gamerRepository = new MemoryGamerRepository();
        IMonsterRepository monsterRepository = new MemoryMonsterRepository();
        List<UserAction> actions = Arrays.asList(
                new CharacterSelection(output),
                new Exit(output)
        );
        new GameRunner(output).init(input, gamerRepository, monsterRepository, actions);
    }
}
