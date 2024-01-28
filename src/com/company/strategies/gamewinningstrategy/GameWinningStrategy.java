package com.company.strategies.gamewinningstrategy;

import com.company.models.Board;
import com.company.models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
    void decreaseSymbolCount(Board board, Move move);
}
