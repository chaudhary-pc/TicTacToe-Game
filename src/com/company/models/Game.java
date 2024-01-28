package com.company.models;

import com.company.exceptions.InvalidGameDimensionException;
import com.company.exceptions.InvalidNumberOfPlayersException;
import com.company.strategies.gamewinningstrategy.GameWinningStrategy;
import com.company.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private GameStatus gameStatus;
    private Board board;
    private List<Move> moves;
    private int nextPlayerIndex;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;
    private Game(){}
    public static Builder getBuilder(){
        return new Builder();
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }
    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }


    /*********************    MAKE NEXT MOVE ********************************************/
    public void makeNextMove(){
        //Steps:
        //1. Player should be able to decide the Move.
        //2. Check the validation of the move, if move is valid then make the move.

        Player playerToMove = players.get(nextPlayerIndex);
        System.out.println("It is "+ playerToMove.getName() + "'s turn");
        Move move = playerToMove.decideMove(board);

        //validate the move
        if(board.validateMove(move)){
            board.applyMove(move);
            moves.add(move);
            //Check the winner. -> Here the game winning strategy is required.
            if(gameWinningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.ENDED;
                winner = playerToMove;
//                this.setWinner(playerToMove);
            }
            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();
        }
        else{
            System.out.println("Cell is already occupied!");
            System.out.println("Enter the available row and col to make a move.");
        }
    }

    /****************************** UNDO ********************************************/
    public void undo(){
        if(moves.size() == 0){
            System.out.println("No moves available for undo");
        }
        else{
            Move lastMove = moves.get(moves.size() - 1);
            moves.remove(moves.size() - 1);
            board.removeMove(lastMove);
            gameWinningStrategy.decreaseSymbolCount(board, lastMove);
            if(nextPlayerIndex == 0) {
                nextPlayerIndex = players.size() - 1;
            }
            else {
                nextPlayerIndex -= 1;
            }
        }
    }

    //We need to use Builder Pattern to build the Game.
    public static class Builder{
        private List<Player> players;
        private int dimension;
        public Builder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public void isValid() throws InvalidGameDimensionException, InvalidNumberOfPlayersException {
            //Validations.
            if (dimension < 3) {
                throw new InvalidGameDimensionException("Dimension can't be less than 3");
            }
            if (players.size() != dimension - 1) {
                throw new InvalidNumberOfPlayersException("Number of players should be 1 less than dimension");
            }
        }
        public Game build(){
            //Before building the game, we should validate the game.
            try{
                isValid();
            }
            catch(InvalidGameDimensionException | InvalidNumberOfPlayersException e){
                System.out.println(e.getMessage());

            }
            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimension));
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }
}
