package service;

import io.Input;
import io.Output;
import model.Gamer;
import model.Monster;
import java.util.List;

public class StartGame {

    private final Input input;
    private final Output output;
    private final Gamer gamer;
    private final List<Monster> monsters;

    public StartGame(Input input, Output output, Gamer gamer, List<Monster> monsters) {
        this.input = input;
        this.output = output;
        this.gamer = gamer;
        this.monsters = monsters;
    }

    public String name() {
        return "Start Game";
    }

    public int execute() throws InterruptedException {
        output.println("==== Start Game ====");
        GameLogic gameLogic = new GameLogic();
        Fight fight = new Fight(gameLogic);
        for (Monster monster : monsters) {
            Thread.sleep(1000);

            output.println(gamer.getName() + " на тебя напал " + monster.getName());
            while (true) {
               if (fight.startOneRoundFight(monster, gamer)) {
                   return -1;
               } else {
                   output.println(gamer.getName() + " ваша очередь");
                   if (fight.startOneRoundFight(gamer, monster)) {
                       break;
                   } else {
                       output.println(monster.getName() + " опять атакует");
                   }
               }

            }
        }
        return 0;
    }
}
