package com.company.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    public Board(int dimension){
        //initialize the board
        board = new ArrayList<>();
        for(int i=0;i<dimension;i++){
            board.add(new ArrayList<>());
            for(int j=0;j<dimension;j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }
    public void displayBoard(){
        for(int i=0;i<board.size();i++){
            for(int j=0;j<board.size();j++){
                if(board.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    System.out.print("|   ");
                }else{
                    System.out.print("| "+ board.get(i).get(j).getPlayer().getSymbol() +" ");
                }
            }
            System.out.println();
        }
    }
    // check if cell is empty or not
    public boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        return this.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }
    // fill the cell and update the player on that cell
    public void applyMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        this.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        this.getBoard().get(row).get(col).setPlayer(move.getPlayer());
    }
    public void removeMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        this.getBoard().get(row).get(col).setCellState(CellState.EMPTY);
        this.getBoard().get(row).get(col).setPlayer(null);
    }
    public List<List<Cell>> getBoard() {
        return board;
    }
    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
