package it.dpg.minigames.base.controller;

import it.dpg.maingame.model.character.Difficulty;

/**
 * Basic interface for minigames
 * @author Davide Picchiotti
 * */

public interface Minigame {

    /**
     * Start the minigame (basically the entry point for it)
     * @return the score obtained in the minigame
     * */
    int start();

    /**
     * Simulates a cpu playing the game
     * @param difficulty the difficulty of the cpu
     * @return the score of the cpu
     * @see Difficulty
     * @see it.dpg.maingame.model.character.Cpu
     * */
    int randomizeScore(Difficulty difficulty);
}
