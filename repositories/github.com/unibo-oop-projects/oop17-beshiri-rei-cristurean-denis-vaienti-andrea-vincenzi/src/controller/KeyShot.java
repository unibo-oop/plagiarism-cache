package controller;

import utility.Command;

/**
 * For add shot and remove shot.
 */
public interface KeyShot {

    /**
     * Add a shot.
     * @param d The direction of shot.
     */
    void addShot(Command d);

    /**
     * Remove a shot.
     * @param d The direction of shot.
     */
    void removeShot(Command d);
}
