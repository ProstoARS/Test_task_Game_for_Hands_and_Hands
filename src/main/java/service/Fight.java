package service;

import exceptions.CharacterDeathException;
import model.Character;

public class Fight {

    private final GameLogic gameLogic;


    public Fight(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public boolean startOneRoundFight(Character characterAttack, Character characterDefence) {
        int attackModifier = gameLogic.attackModifier(characterAttack.getAttack(), characterDefence.getProtection());
        boolean checkAttack = gameLogic.checkAttack(attackModifier);
        if (checkAttack) {
            int attackValue = characterAttack.getDamage().attackValue();
            System.out.printf("%s наносит %s урона\n\r", characterAttack.getName(), attackValue);
            try {
                gameLogic.healthDecrease(characterDefence, attackValue);
            } catch (CharacterDeathException e) {
                System.out.printf("Персонаж %s умер.\n\r", characterDefence.getName());
                return true;
            }
            System.out.printf("Здоровье персонажа %s уменьшилось на %s и стало равно %s hp.\n\r",
                    characterDefence.getName(), attackValue, characterDefence.getHealth());
        } else {
            System.out.printf("Персонаж %s отразил атаку\n\r", characterDefence.getName());
        }
        return false;
    }
}
