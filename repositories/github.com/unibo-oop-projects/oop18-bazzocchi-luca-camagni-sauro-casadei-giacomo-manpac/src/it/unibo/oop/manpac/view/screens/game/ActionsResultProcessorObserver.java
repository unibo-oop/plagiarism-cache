package it.unibo.oop.manpac.view.screens.game;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Entity;
/**
 * Lightens the GameView from collision results and input methods
 * (via Observer Pattern, this is the Observer).
 */
public interface ActionsResultProcessorObserver {

    /**
     * Pacman has just collided with something and something happened.
     * @param action what happened in that collision
     */
    void pacmanCollisionHelper(Action action);

    /**
     * An input keys has just been pressed, and something happened.
     * @param action what happened after key pressure
     */
    void pacmanChangeDirectionHelper(Action action);

    /**
     * A phantom changed its direction.
     * @param phantomName the phantom which direction changed
     * @param action the action that represents the new direction
     */
    void phantomChangeDirectionHelper(Entity phantomName, Action action);

}
