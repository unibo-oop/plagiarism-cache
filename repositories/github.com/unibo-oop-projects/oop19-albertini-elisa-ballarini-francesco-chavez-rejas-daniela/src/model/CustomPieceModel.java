package model;

import java.awt.Color;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import login.Player;
import pair.Pair;
import piece.Piece;

/**
 * Interface for the model part of the creation of a custom piece in the
 * application.
 */
public interface CustomPieceModel {

    /**
     * @param hit : the coordinates, as a Pair, to add at the list of the squares
     *            hit by the user.
     */
    void add(Pair<Integer, Integer> hit);

    /**
     * Method that deletes the square passed as parameter from the grid.
     * 
     * @param deleteSquare : the coordinates, as a Pair, to remove by the list of
     *                     squares hit by the user.
     */
    void delete(Pair<Integer, Integer> deleteSquare);

    /**
     * Method that sets the new color of the squares based on the parameter,
     * replacing the old one.
     * 
     * @param newColor : the new color of the squares.
     */
    void setColor(Color newColor);

    /**
     * @return the current color of the squares.
     */
    Color getCurrentColor();

    /**
     * Method that return the squares hit at the moment.
     * 
     * @return the set of coordinates of the squares hit by the user.
     */
    Set<Pair<Integer, Integer>> getHitSquares();

    /**
     * Method that sets to the default value all the parameters.
     */
    void refresh();


    /**
     * @param checkList : list of actual custom pieces to use during the game.
     * @return true if the custom piece the user is going to save is not present in the actual list of custom pieces, false otherwise.
     */
    boolean isUnique(Optional<List<Piece>> checkList);

    /**
     * Method that saves in a temporary list a new custom piece that can be used
     * during the game.
     * 
     * @param rtList : the temporary list in which will be save the new custom piece,
     *                it can be empty if the user has not add any custom piece.
     */
    void saveRtPiece(Optional<List<Piece>> rtList);

    /**
     * Method that saves permanently in the logged user's JSON file a new custom
     * piece that can be used during the game.
     * 
     * @param player : the current logged user in which will be added a new custom
     *                piece in his custom piece list.
     */
    void savePlayerPiece(Player player);

}
