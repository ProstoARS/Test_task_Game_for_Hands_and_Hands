package service;

import exceptions.CharacterDeathException;
import io.Input;
import io.Output;
import model.Gamer;
import model.Monster;

import java.util.List;

import static java.lang.System.*;
import static service.WinLooseConstant.*;

public class StartGame {

    private final Input input;
    private final Output output;
    private final Fight fight;

    public StartGame(Input input,
                     Output output,
                     Fight fight) {
        this.input = input;
        this.output = output;
        this.fight = fight;
    }

    public String name() {
        return "Start Game";
    }

    public int execute(Gamer gamer, List<Monster> monsters) throws InterruptedException, CharacterDeathException {
        output.println("==== Игра началась ====");

        int recoveryAction = 4;

        for (Monster monster : monsters) {
            Thread.sleep(1000);
            int action;
            output.println(lineSeparator() + "--- " + gamer.getName()
                           + " на тебя напал " + monster.getName() + " ---");
            output.println("У " + monster.getName() + " " + monster.getHealth() + " здоровья");
            while (true) {
                Thread.sleep(1000);
                if (fight.startOneRoundFight(monster, gamer)) {
                    return LOOSE_OUT_GAME;
                } else {
                    output.println("--- " + gamer.getName() + " ваша очередь ---" + lineSeparator());
                    action = gamerAction();
                }
                if (action == 1) {
                    if (fight.startOneRoundFight(gamer, monster)) {
                        break;
                    } else {
                        output.println(monster.getName() + " опять атакует");
                    }
                } else {
                    if (recoveryAction > 0) {
                        output.println("Вы восстановили здоровье на: " + gamer.recoveryHealth()
                                       + " теперь оно равняется " + gamer.getHealth());
                        recoveryAction--;
                        output.println("Лечений осталось: " + recoveryAction);
                    } else {
                        output.println("Вы больше не можете лечиться");
                    }
                }
            }
        }
        return WINNER_OUT_GAME;
    }

    private int gamerAction() {
        int action;
        while (true) {
            output.println("Ваше действие:\n\r 1. Атаковать\n\r 2. Лечиться");
            action = input.askInt("Выберите: ");
            if (action == 1 || action == 2) {
                break;
            }
            output.println("Введите значения из меню");
        }
        return action;
    }

}

