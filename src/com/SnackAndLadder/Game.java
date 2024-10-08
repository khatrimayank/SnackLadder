package com.SnackAndLadder;
import java.util.*;

class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int currentPlayerIndex;

    public Game(int boardSize, List<String> playerNames) {
        board = new Board(boardSize);
        dice = new Dice();
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        currentPlayerIndex = 0;
    }
    
    // Method to add a snake to the board
    public void addSnake(int start, int end) {
        Snake snake = new Snake(start, end);
        board.addSnake(snake);
    }

    // Method to add a ladder to the board
    public void addLadder(int start, int end) {
        Ladder ladder = new Ladder(start, end);
        board.addLadder(ladder);
    }
    
    public void start() {
        boolean gameWon = false;
        while (!gameWon) {
            gameWon = playTurn();
        }
        System.out.println("Player " + players.get(currentPlayerIndex).getName() + " wins!");
    }

    private boolean playTurn() {
    	
        Player currentPlayer = players.get(currentPlayerIndex);
        int diceRoll = dice.roll();
        System.out.println(currentPlayer.getName() + " rolled " + diceRoll);

        if (currentPlayer.getPosition() == 0 && diceRoll != 1 && diceRoll != 6) {
            nextPlayer();
            System.out.println(currentPlayer.getName() + " is at 0 positon and didn't get 1 or 6 to open token" );
            return false;
        }


        currentPlayer.setPosition(movePlayer(currentPlayer, diceRoll));
        if (currentPlayer.getPosition() == board.getSize() * board.getSize() - 1) {
            return true;
        }
        
        System.out.println(currentPlayer.getName() + " is at position : " + currentPlayer.getPosition());

        nextPlayer();
        return false;
    }

    private int movePlayer(Player player, int diceRoll) {
        int newPosition = player.getPosition() + diceRoll;
        if (!board.isValidPosition(newPosition)) {
            return player.getPosition();
        }

        newPosition = board.getNewPosition(newPosition);
        
        return newPosition;
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public static void main(String[] args) {
        List<String> playerNames = Arrays.asList("Player1", "Player2");
        Game game = new Game(10, playerNames);
        game.addSnake(14, 7);
        game.addSnake(22, 3);
        game.addLadder(2, 15);
        game.addLadder(8, 23);

        game.start();
    }
}

// using Hashmap to store player's name and Player Object insted of using index of Player
/*import java.util.*;

public class Game {
    private Board board;
    private Map<String, Player> players;
    private Dice dice;
    private String currentPlayerName;

    public Game(int boardSize, List<String> playerNames) {
        board = new Board(boardSize);
        dice = new Dice();
        players = new HashMap<>();
        for (String name : playerNames) {
            players.put(name, new Player(name));
        }
        currentPlayerName = playerNames.get(0);
    }

    public void start() {
        boolean gameWon = false;
        while (!gameWon) {
            gameWon = playTurn();
        }
        System.out.println("Player " + currentPlayerName + " wins!");
    }

    private boolean playTurn() {
        Player currentPlayer = players.get(currentPlayerName);
        int diceRoll = dice.roll();
        System.out.println(currentPlayerName + " rolled " + diceRoll);

        if (currentPlayer.getPosition() == 0 && diceRoll != 1 && diceRoll != 6) {
            nextPlayer();
            return false;
        }

        if (diceRoll == 6) {
            // Handle three consecutive sixes
            int consecutiveSixes = 1;
            while (consecutiveSixes < 3 && diceRoll == 6) {
                currentPlayer.setPosition(movePlayer(currentPlayer, diceRoll));
                diceRoll = dice.roll();
                System.out.println(currentPlayerName + " rolled " + diceRoll);
                consecutiveSixes++;
            }
            if (consecutiveSixes == 3) {
                System.out.println(currentPlayerName + " rolled three consecutive sixes, losing their turn.");
                nextPlayer();
                return false;
            }
        }

        currentPlayer.setPosition(movePlayer(currentPlayer, diceRoll));
        if (currentPlayer.getPosition() == board.getSize() * board.getSize() - 1) {
            return true;
        }

        nextPlayer();
        return false;
    }

    private int movePlayer(Player player, int diceRoll) {
        int newPosition = player.getPosition() + diceRoll;
        if (!board.isValidPosition(newPosition)) {
            return player.getPosition();
        }

        newPosition = board.getNewPosition(newPosition);
        return newPosition;
    }

    private void nextPlayer() {
        List<String> playerNames = new ArrayList<>(players.keySet());
        int index = playerNames.indexOf(currentPlayerName);
        currentPlayerName = playerNames.get((index + 1) % playerNames.size());
    }

    public static void main(String[] args) {
        List<String> playerNames = Arrays.asList("Player1", "Player2");
        Game game = new Game(10, playerNames);
        game.start();
    }
}
*/
