package it.unibo.biscia.view;

import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionSubject;
import it.unibo.biscia.events.StateObserver;

import java.util.List;

/**
 * The View is the front-end fo the application. Every implementation of it,
 * should agree its contract or it's not a View implementation. The View is an
 * {@link ActionSubject} for comunicating the actions fo the player and also a
 * {@link StateObserver} to react to changes of the game's state.
 * 
 * @see Controller
 * @see ActionSubject
 * @see StateObserver
 *
 */
public interface View extends ActionSubject, StateObserver {

    /**
     * Set how many and who are the players playing.
     * 
     * @param players How many and who are the players playing
     */
    void setPlayers(List<Player> players);

}
