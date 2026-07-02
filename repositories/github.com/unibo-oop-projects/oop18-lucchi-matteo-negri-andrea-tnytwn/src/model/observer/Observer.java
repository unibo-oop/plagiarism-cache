package model.observer;

import model.game.Game;

/**
 * Interface used for the Achievements.
 */
public interface Observer {

    /**
     * Method called when something change in game. Check if the Achievement has been unlocked or not.
     * @param game
     *          class with information about the required items
     */
    void update(Game game);
}
