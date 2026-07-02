package controller;

import java.awt.Color;
import java.util.Set;

import pair.Pair;

/**
 * Specific interface for the controller of the custom piece part.
 *
 */
public interface ControllerCustomPiece extends Controller {

    /**
     * Method that change the color of the pieces if the newColor is different from
     * the old one.
     * 
     * @param newColor : color with which will be replaced the old one.
     */
    void setCurrentColor(Color newColor);

    /**
     * Method that update the set of hit squares. If the parameter is in the set it
     * is removed, on the other hand it is added to it.
     * 
     * @param hitSquare : the pair that has been hit by the user.
     */
    void hit(Pair<Integer, Integer> hitSquare);

    /**
     * Method that controls that the squares hit by the user respect certain rules,
     * before to save them as a new custom piece in a file.
     */
    void saveAttempt();

    /**
     * Method that call the model method to set the data to the default values.
     */
    void clearData();

    /**
     * @return the set of squares that have been hit.
     */
    Set<Pair<Integer, Integer>> getSquares();

}
