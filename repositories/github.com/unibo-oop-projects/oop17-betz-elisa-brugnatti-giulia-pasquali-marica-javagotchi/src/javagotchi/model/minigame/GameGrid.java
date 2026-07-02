package javagotchi.model.minigame;

import java.util.List;
import org.apache.commons.lang3.Pair;

/**
 * 
 * @author marica
 *
 */
public interface GameGrid {

    /**
     * Gets rows and columns of game grid.
     * 
     * @return dimensions of game grid
     */
    Pair<Integer, Integer> getDimensionGame();

    /**
     * Gets list of coordinates of the game buttons.
     * 
     * @return list of coordinates
     */
    List<Pair<Integer, Integer>> getCoords();

    /**
     * Moves coordinates in the panel game.
     */
    void move();

    /**
     * Gets InitialRow.
     * 
     * @return initial row
     */
    int getInitialRow();

    /**
     * Indicates if the player has presses the game button in the initial row.
     * 
     * @param row
     *            selected row
     * @return true if row is initial value
     */
    boolean isValStart(int row);

}
