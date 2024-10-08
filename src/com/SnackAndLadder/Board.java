package com.SnackAndLadder;
import java.util.*;

class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void addSnake(Snake snake) {
        snakes.add(snake);
    }

    public void addLadder(Ladder ladder) {
        ladders.add(ladder);
    }

    public int getNewPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.getStart() == position) {
            	System.out.println("At position " + position + " snake is present , Player is bitten and reach at " + snake.getEnd());
                return snake.getEnd();
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) {
            	System.out.println("At position " + position + " Ladder is present , Player climbs ladder and reach at " + ladder.getEnd());
                return ladder.getEnd();
            }
        }
        return position;
    }

    public boolean isValidPosition(int position) {
        return position >= 0 && position < size * size;
    }
}