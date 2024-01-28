package com.company.strategies.botplayingstrategy;

import com.company.models.Board;
import com.company.models.Move;
import com.company.models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board);
}
