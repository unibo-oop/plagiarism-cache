package it.oop.project.model;

import java.awt.Point;
import java.util.List;

import it.oop.project.util.Direction;
import it.oop.project.util.SquareMatrix;

/**
 * The 2048 classic game model.
 *
 */
public interface GameModel {

    /**
     * Resets this model at initial state.
     */
    void reset();

    /**
     * Returns the current state of the board.
     * 
     * @return a square matrix with the current state of the board.
     */
    SquareMatrix getBoard();

    /**
     * Returns the current score.
     * 
     * @return the current score.
     */
    int getScore();

    /**
     * Returns the best score.
     * 
     * @return the best score.
     */
    int getBestScore();

    /**
     * Returns a list with coordinates of spawned numbers.
     * 
     * @return a list with coordinates of spawned numbers.
     */
    List<Point> getSpawnCoordinates();

    /**
     * Returns a list with coordinates of fused numbers.
     * 
     * @return a list with coordinates of fused numbers.
     */
    List<Point> getFusionCoordinates();

    /**
     * Returns if volume is on.
     * 
     * @return true if volume is on, false otherwise.
     */
    boolean isVolumeOn();

    /**
     * Sets this won displayed.
     */
    void setWonDisplayed();

    /**
     * Sets volume on or off depending on previous state.
     */
    void setVolume();

    /**
     * Shifts all numbers in the specified direction, fuse them when opportune
     * and spawn a new number.
     * 
     * @param direction
     *            direction where to shift.
     */
    void shift(final Direction direction);

    /**
     * Returns if 2048 is achieved for the first time in the current game.
     * 
     * @return true if 2048 is achieved for the first time in the current game,
     *         false if not.
     */
    boolean hasWon();

    /**
     * Returns if there are moves available.
     * 
     * @return true if there are moves available, false if not.
     */
    boolean noMovesAvailable();

    /**
     * Writes or overwrites a savegame with current state in user folder.
     */
    void writeSavegame();

}
