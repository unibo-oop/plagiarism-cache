package gamelogic;

import java.awt.Color;
import java.util.Set;
import java.util.Map;
import pair.Pair;

/**
 * This interface models the board, it saves the position of the obstacles, checks if there is any completed row and eventually 
 * delete those and moves the rest of the obstacles.
 *
 */
public interface Board {

    /**
     * This static variable is the length of the columns.
     */
    int COLLENGTH = 20;

    /**
     * This static variable is the length of the rows.
     */
    int ROWLENGTH = 10;

    /**
     * This method saves the coordinates of the current on the board as a new
     * obstacle.
     */
    void placePiece();

    /**
     * This method deletes the lines passed in input.
     * 
     * @param rows to delete
     */
    void cancelLines(Set<Integer> rows);

    /**
     * @return the board
     */
    Map<Pair<Integer, Integer>, Color> getBoard();

    /**
     * This method checks if there are lines completed and calls the cancelLines on
     * them.
     */
    void findRowsCompleted();

}
