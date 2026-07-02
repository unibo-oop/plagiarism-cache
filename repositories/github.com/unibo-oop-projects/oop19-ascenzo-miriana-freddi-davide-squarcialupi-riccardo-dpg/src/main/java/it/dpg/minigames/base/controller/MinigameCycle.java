package it.dpg.minigames.base.controller;

/**
 * Interface for a basic game cycle for minigames
 * @author Davide Picchiotti
 * */

public interface MinigameCycle {

    /**
     * Start the cycle of the minigame
     * @return the score obtained in the minigame
     * */
    int startCycle();
}
