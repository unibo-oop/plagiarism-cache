package it.oop.project.view;

import java.awt.Point;
import java.util.List;

import it.oop.project.controller.GameController;
import it.oop.project.util.SquareMatrix;

/**
 * This is the 2048 game view.
 *
 */
public interface GameView {

    /**
     * Updates the view in the current state of the game.
     * 
     * @param stateMatrix
     *            a matrix with current numbers in current positions.
     * @param spawnedCoordinates
     *            a list with coordinates of spawned numbers.
     * @param fusionCoordinates
     *            a list with coordinates of fusion numbers.
     * @param score
     *            current score.
     * @param bestScore
     *            current best score.
     */
    void update(final SquareMatrix stateMatrix,
            final List<Point> spawnedCoordinates,
            final List<Point> fusionCoordinates, final int score,
            final int bestScore);

    /**
     * Displays the view.
     * 
     */
    void display();

    /**
     * Displays win dialog.
     * 
     */
    void showWinDialog();

    /**
     * Displays lose dialog.
     * 
     */
    void showLoseDialog();

    /**
     * Sets the controller of this view.
     * 
     * @param controller
     *            the controller to set.
     */
    void setController(GameController controller);

    /**
     * Sets the volume icon to the correct one.
     * 
     * @param volumeOn
     *            specifies if the volume is on or not.
     */
    void setVolumeIcon(boolean volumeOn);
}
