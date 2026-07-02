package it.dpg.maingame.controller.gamecycle;

import org.apache.commons.lang3.tuple.Pair;

/**
 * coordinates the elements of the game
 */
public interface GameCycle {
    /**
     * start the game cycle with a new thread
     */
    void startGameCycle();

    /**
     * method used to notify the game cycle the dice has been thrown by a player
     */
    void signalDiceThrown();

    /**
     * method used to notify the path has been chosen by a player
     */
    void signalPathChosen(Pair<Integer, Integer> coordinates);

    /**
     * signals the button associated to the next step of the turn is pressed (ex. Enter)
     */
    void signalNextStep();
}
