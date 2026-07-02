package it.unibo.biscia.core;

import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.StateSubject;

import java.util.List;

/**
 * this interface from core to external boundary (view).
 *
 */
public interface Controller extends ActionObserver, StateSubject {
    /**
     * max speed for game.
     */
    int MAX_SPEED = 100;

    /**
     * possibles speed for game.
     *
     */
    enum Speed {
        STATIC, SPEED1, SPEED2, SPEED3, SPEED4, SPEED5, SPEED6, SPEED7, SPEED8, SPEED9, MAX;
    }

    /**
     * list of player of game.
     * 
     * @return list of playero on game
     */
    List<Player> getPlayers();

    /**
     * start the game, first action notify pause and newlevel to StateObservers.
     */
    void start();
}
