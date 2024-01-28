package com.company;

import com.company.controllers.GameController;
import com.company.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) {
        List<Player> players  = new ArrayList<>();

        System.out.println("Game is starting......");
        Scanner sc = new Scanner(System.in);

        System.out.println("What is the dimensions of the game: ");
        int dimension = sc.nextInt();

        System.out.println("Will there any bot in game: y/n");
        String isBot = sc.next();
        int noOfHumanPlayers = dimension - 1;
        if(isBot.equals("y")) {
            noOfHumanPlayers = dimension - 2;
            System.out.println("Enter the name of the Bot: ");
            String name = sc.next();

            System.out.println("Enter the symbol for the Bot: ");
            String symbol = sc.next();

            players.add(new Bot(name, symbol.charAt(0), BotDifficultyLevel.EASY));
        }

        for(int i=0;i<noOfHumanPlayers;i++){
            System.out.println("Enter the name of the player: " + (i+1));
            String name = sc.next();

            System.out.println("Enter the symbol for the player: "+(i+1));
            String symbol = sc.next();

            players.add(new Player(name, symbol.charAt(0), PlayerType.HUMAN));
        }

        // use controller to just add a layer between client and game
        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);

        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            // Player will play the game
            System.out.println("\nThis is the current board: ");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo: y/n");
            String undoInput = sc.next();
            if(undoInput.equals("y")){
                gameController.undo(game);
            }
            else{
                gameController.executeNextMove(game);
            }
        }

        if(gameController.getGameStatus(game).equals(GameStatus.DRAW)) {
            System.out.println("Game has Drawn!!");
        }
        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)){
            //someone has won the game
            System.out.println();
            System.out.println("GAME ENDED!!  Final Board: ");
            gameController.displayBoard(game);
            System.out.println("Congratulations "+gameController.getWinner(game).getName() +" won the game!");
        }

    }
}
