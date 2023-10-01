package service;

import exceptions.CharacterDeathException;
import model.Character;

public class Fight {

    private final GameLogic gameLogic;

    private final Character character;

    public Fight(GameLogic gameLogic, Character character) {
        this.gameLogic = gameLogic;
        this.character = character;
    }

    public void startOneRoundFight(Character characterAttack, Character characterDefence) {
        int attackModifier = gameLogic.attackModifier(characterAttack.getAttack(), characterDefence.getProtection());
        boolean checkAttack = gameLogic.checkAttack(attackModifier);
        if (checkAttack) {
            int attackValue = characterAttack.getDamage().attackValue();
            int healthDecrease = 0;
            try {
                healthDecrease = gameLogic.healthDecrease(characterDefence, attackValue);
            } catch (CharacterDeathException e) {
                System.out.printf("Персонаж %s умер.", character.getName());
            }
            System.out.printf("Здоровье персонажа %s уменьшилось на %s и стало равно %s hp.",
                    characterDefence.getName(), healthDecrease, characterDefence.getHealth());
        }
    }
}
