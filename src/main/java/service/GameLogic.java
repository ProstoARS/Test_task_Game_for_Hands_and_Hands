package service;

import java.util.Random;

public class GameLogic {


    public static int attackModifier(int attack, int protection) {
        return attack - protection + 1;
    }

    public static int gameCubeAction() {
        return new Random().nextInt(6) + 1;
    }
}
