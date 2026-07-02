package model;

import java.util.List;
import javafx.util.Pair;
import com.google.common.base.Optional;

/**
 * 
 * Interface for SudokuLogicImpl.
 * 
 */
public interface SudokuLogic {

    /**
     * Set one element in the grid.
     * @param x Row of the element
     * @param y Column of the element
     * @param value Value of the element
     */
    void hit(int x, int y, int value);

    /**
     * Remove one element from the grid.
     * @param x Row of the element
     * @param y Column of the element
     */
    void remove(int x, int y);

    /**
     * @return Grid is solved
     */
    boolean isDone();

    /**
     * @return The status of the board
     */
    List<Pair<Optional<Integer>, Boolean>> getValues();

    /**
     * @return Initial grid
     */
    String[] getInitialGrid();

    /**
     * @return Size of the grid
     */
    int getSize();

    /**
     * @return SquareSize of the grid
     */
    int getSquareSize();
}
