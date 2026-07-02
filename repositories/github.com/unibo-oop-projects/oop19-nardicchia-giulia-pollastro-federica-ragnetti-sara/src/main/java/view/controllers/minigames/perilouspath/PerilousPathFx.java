package view.controllers.minigames.perilouspath;

import java.util.Set;

import model.minigames.perilouspath.Mine;
import utility.Pair;
import view.controllers.Area;

/**
 * Represents the game's view.
 */
@Area(trainingArea = "Memory")
public interface PerilousPathFx {

    /**
     * Draw the grid.
     * 
     * @param size
     *          the dimension of the grid
     */
    void draw(int size);

    /**
     * Wait a few milliseconds to show the mines or a mine explosion.
     * 
     * @param millis
     *              milliseconds it has to wait
     * @param mineExplosion
     *              true if the mine is exploded, false otherwise
     */
    void animation(int millis, boolean mineExplosion);

    /**
     * Shows the starting point from which the user must depart.
     * 
     * @param start
     *              the initial step
     */
    void showStart(Pair<Integer, Integer> start);

    /**
     * Shows the target that the user must reach.
     * 
     * @param finish
     *              the end step
     */
    void showFinish(Pair<Integer, Integer> finish);

    /**
     * Shows the mines that the user must avoid.
     * 
     * @param mines
     *              the mines to show
     */
    void showMines(Set<Mine> mines);

    /**
     * Shows the step of the path taken by the user.
     * 
     * @param row
     *          the step's position row
     * @param col
     *          the step's position col
     */
    void showStep(int row, int col);

    /**
     * Shows the mine exploded.
     * 
     * @param row
     *          the mine position row
     * @param col
     *          the mine position col
     */
    void showExplosion(int row, int col);

    /**
     * Restore the view.
     */
    void restore();
}
