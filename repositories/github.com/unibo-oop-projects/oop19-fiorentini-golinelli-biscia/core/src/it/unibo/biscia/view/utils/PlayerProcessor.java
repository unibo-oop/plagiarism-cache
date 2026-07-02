package it.unibo.biscia.view.utils;

import it.unibo.biscia.core.EntityType;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.ActionSubject;
import it.unibo.biscia.events.GenericEventSubject;

/**
 * Assign to a precise {@link Player} all possible {@link EntityType#SNAKE}
 * moves defined by {@link ActionObserver} and submit them via
 * {@link ActionSubject}. Moves are notified when appropriate keyboard's events
 * occurs.
 *
 * @see PlayerProcessorImpl
 * @see PlayerOneProcessor
 * @see PlayerTwoProcessor
 */
public interface PlayerProcessor {

    /**
     * The {@link Player} to process the input.
     * 
     * @return A {@link Player}
     */
    Player getPlayer();

    /**
     * The subject used to notify moves via {@link GenericEventSubject#notify()}.
     * 
     * @return A {@link GenericEventSubject}
     */
    GenericEventSubject<ActionObserver> getSubject();

    /**
     * Move corresponding to {@link EntityType#SNAKE} going "up". (Arrow up key is
     * processed).
     */
    void moveUp();

    /**
     * Move corresponding to {@link EntityType#SNAKE} going "down". (Arrow down key
     * is processed).
     */
    void moveDown();

    /**
     * Move corresponding to {@link EntityType#SNAKE} going "left". (Arrow left key
     * is processed).
     */
    void moveLeft();

    /**
     * Move corresponding to {@link EntityType#SNAKE} going "right". (Arrow right
     * key is processed).
     */
    void moveRigth();

}
