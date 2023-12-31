import exceptions.CharacterDeathException;
import io.*;
import model.Gamer;
import model.Monster;
import repository.IGamerRepository;
import repository.IMonsterRepository;
import repository.MemoryGamerRepository;
import repository.MemoryMonsterRepository;
import service.*;

import static java.lang.System.*;
import static service.WinLooseConstant.*;

import java.util.Arrays;
import java.util.List;

public class GameRunner {

    private final Output out;

    public GameRunner(Output out) {
        this.out = out;
    }

    public void exe(Input input,
                    IGamerRepository gamerRepository,
                    IMonsterRepository monsterRepository,
                    List<UserAction> actions,
                    StartGame startGame) {
        int run = 0;
        while (run >= 0) {
            this.showMenu(actions);
            int select = input.askInt("Выберите: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Неправильный ввод, выберите: 0 .. " + (actions.size() - 1));
                continue;
            }
            gamerRepository.init();
            monsterRepository.init();
            UserAction action = actions.get(select);
            run = action.execute(input, gamerRepository, monsterRepository);
            if (run == -1) {
                continue;
            }
            Gamer gamer = gamerRepository.findById(run);
            List<Monster> monsterList = monsterRepository.findAll();
            try {
                run = startGame.execute(gamer, monsterList);
            } catch (InterruptedException | CharacterDeathException e) {
                System.out.println(e.getMessage());
            }
            if (run == WINNER_OUT_GAME) {
                out.println(lineSeparator() + "--- Поздравляем, вы победили! ---" + lineSeparator());
            } else if (run == LOOSE_OUT_GAME) {
                out.println(lineSeparator() + "--- Вы проиграли ---" + lineSeparator());
            }
        }
        out.println("----- Игра закончилась -----");
    }

    private void showMenu(List<UserAction> actions) {
        out.println("==== Меню ====");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        IGamerRepository gamerRepository = new MemoryGamerRepository();
        IMonsterRepository monsterRepository = new MemoryMonsterRepository();
        GameLogic gameLogic = new GameLogic();
        Fight fight = new Fight(gameLogic);
        StartGame startGame = new StartGame(input, output, fight);
        List<UserAction> actions = Arrays.asList(
                new CharacterSelection(output),
                new Exit(output)
        );
        new GameRunner(output).exe(input, gamerRepository, monsterRepository, actions, startGame);
    }
}
