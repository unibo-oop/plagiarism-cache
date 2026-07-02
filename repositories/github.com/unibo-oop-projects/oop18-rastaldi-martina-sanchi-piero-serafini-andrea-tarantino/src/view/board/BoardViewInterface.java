package view.board;

import javax.swing.JButton;

import javafx.util.Pair;
import model.elements.Element;
import utilities.Colors;
import utilities.Status;
import view.board.sideview.GameView;

/**
 * Interface for the main game board view.
 * Andrea Serafini.
 *
 */
public interface BoardViewInterface {

    /**
     * Close the window.
     */
    void close();

    /**
     *
     * @param oldSelected
     *            coordinates where to draw the background
     * @param pair
     *            what to draw
     */
    void drawBackground(Pair<Integer, Integer> oldSelected, Pair<Status, Colors> pair);

    /**
     *
     * @param selected
     *            coordinates where to draw the element
     * @param elementAtPosition
     *            element to be drawn
     */
    void drawElement(Pair<Integer, Integer> selected, Element elementAtPosition);

    /**
     *
     * @return a clone of the grid
     */
    JButton[][] getGrid();

    /**
     *
     * @return the side panel
     */
    GameView getSidePanel();

    /**
     *
     * @param coord
     *            to be set with the selected hero icon
     */
    void selectedIcon(Pair<Integer, Integer> coord);

    /**
     * Show the board.
     */
    void show();

    /**
     * Wait for a dice roll.
     */
    void waitDice();

    /**
     * Wait for the player to move.
     */
    void waitMove();

    /**
     *
     * @param actualPosition
     *            the cell to enable for the selection
     */
    void waitSelect(Pair<Integer, Integer> actualPosition);

}
