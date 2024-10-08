package com.SnackAndLadder;
import java.util.Random;

class Dice {
    private Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1;
    }
}