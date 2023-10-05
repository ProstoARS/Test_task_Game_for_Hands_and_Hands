package service;


import exceptions.CharacterDeathException;
import model.Character;

import java.util.Random;

public class GameLogic {

    public static final int CUBE_SIDES = 6;
    public static final int INDENT = 1;
    public static final int MODIFIER_CONSTANT = 1;

    public static int gameCubeAction() {
        return new Random().nextInt(CUBE_SIDES) + INDENT;
    }

    public int attackModifier(int attack, int protection) {
        if (attack <= protection) {
            return MODIFIER_CONSTANT;
        }
        return attack - protection + MODIFIER_CONSTANT;
    }

    public boolean checkAttack(int attackModifier) {
        boolean check = false;
        int countCube = 0;
        do {
            countCube++;
            int rsl = gameCubeAction();
            if (rsl == 5 || rsl == 6) {
                check = true;
                break;
            }
        } while (countCube < attackModifier);
        return check;
    }

    public int healthDecrease(Character character, int attackValue) throws CharacterDeathException {
        int health = character.getHealth();
        int newHeath = health - attackValue;
        character.setHealth(newHeath);
        return newHeath;
    }

}

