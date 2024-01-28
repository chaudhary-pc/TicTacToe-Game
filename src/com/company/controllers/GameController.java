package com.company.controllers;


import com.company.models.Game;
import com.company.models.GameStatus;
import com.company.models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players){
        Game game  = Game.getBuilder().setDimension(dimension).setPlayers(players).build();
        return game;
    }
    public void undo(Game game){
        game.undo();
    }
    public void executeNextMove(Game game){
        game.makeNextMove();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }
}
