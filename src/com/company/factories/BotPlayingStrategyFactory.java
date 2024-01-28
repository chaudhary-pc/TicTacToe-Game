package com.company.factories;

import com.company.models.BotDifficultyLevel;
import com.company.strategies.botplayingstrategy.BotPlayingStrategy;
import com.company.strategies.botplayingstrategy.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }
//        else if(botDifficultyLevel.equals(BotDifficultyLevel.HARD)){
//            //call hard bot playing strategy
//
//        }
        return null;
    }
}
