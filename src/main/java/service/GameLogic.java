package service;


import java.util.Random;

public class GameLogic {

    public static int gameCubeAction() {
        return new Random().nextInt(6) + 1;
    }

    public int attackModifier(int attack, int protection) {
        return attack - protection + 1;
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

}

