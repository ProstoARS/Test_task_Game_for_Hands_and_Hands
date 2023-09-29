package model;

import java.util.Random;

public class Damage {

    public static final int INDENTATION = 1;
    private final int from;
    private final int to;

    public Damage(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int attackValue() {
        int delta = to - from;
        return new Random().nextInt(delta + INDENTATION) + from;
    }
}
