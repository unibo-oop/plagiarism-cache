package it.unibo.the100dayswar.model.turn.api;

import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;

/** 
 * An interface for the day in the game.
 */
public interface GameDay extends ResourceGenerator {
    /**
     * Increase the counter of the day.
     */
    void increaseDay();
    /**
     * This method returns the current day.
     * 
     * @return the current day
     * 
     */
    int getCurrentDay();

    /**
     * notify the observers.
     */
    void notifyObservers();

    /**
     * retun the max day in a Game.
     * @return return the max day
     */
    int getMaxDay();
}
