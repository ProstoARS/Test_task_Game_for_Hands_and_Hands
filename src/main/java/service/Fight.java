package service;

import exceptions.CharacterDeathException;
import model.Character;

public class Fight {

    private final GameLogic gameLogic;


    public Fight(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void startOneRoundFight(Character characterAttack, Character characterDefence) {
        int attackModifier = gameLogic.attackModifier(characterAttack.getAttack(), characterDefence.getProtection());
        boolean checkAttack = gameLogic.checkAttack(attackModifier);
        if (checkAttack) {
            int attackValue = characterAttack.getDamage().attackValue();
            try {
                gameLogic.healthDecrease(characterDefence, attackValue);
            } catch (CharacterDeathException e) {
                System.out.printf("Персонаж %s умер.", characterDefence.getName());
                return;
            }
            System.out.printf("Здоровье персонажа %s уменьшилось на %s и стало равно %s hp.",
                    characterDefence.getName(), attackValue, characterDefence.getHealth());
        } else {
            System.out.printf("Персонаж %s отразил атаку", characterDefence.getName());
        }
    }
}
