package it.dpg.minigames.punchygame.view;

import it.dpg.maingame.view.View;
import it.dpg.minigames.punchygame.controller.input.InputObserver;
import it.dpg.minigames.punchygame.model.Direction;

import java.util.List;

/**
 * Interface used to interact with the view of PunchyMinigame
 * @author Davide Picchiotti
 * @see it.dpg.minigames.punchygame.PunchyMinigame
 * */

public interface PunchygameView extends View {
    /**
     * Update the boxing sacks on the view
     * @param sacks a ordered list of sacks with their directions respective to the player
     * */
    void updateSacks(final List<Direction> sacks);

    /**
     * Update the score on the view
     * @param score the score
     * @param combo consecutive hit done by the player
     * */
    void updateScore(final int score, final int combo);

    /**
     * Update the timer in the view
     * @param timer the remaining time
     * */
    void updateTimer(final float timer);

    /**
     * Update the boxer on the view
     * @param boxerDirection the direction in which the boxer is faced
     * */
    void updateBoxer(final Direction boxerDirection);

    /**
     * Play the sound used when punches hit
     * */
    void playPunchSound();

    /**
     * Play the sound used when punches miss
     * */
    void playMissSound();

    /**
     * Set the InputObserver for this view
     * @param observer the observer for user input
     * */
    void setInputObserver(final InputObserver observer);
}
