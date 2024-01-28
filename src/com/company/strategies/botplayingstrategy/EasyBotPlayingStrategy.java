package com.company.strategies.botplayingstrategy;

import com.company.models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move decideMove(Player player, Board board){
        int size = board.getBoard().size();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    //Bot will make a move
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null;
    }
}
