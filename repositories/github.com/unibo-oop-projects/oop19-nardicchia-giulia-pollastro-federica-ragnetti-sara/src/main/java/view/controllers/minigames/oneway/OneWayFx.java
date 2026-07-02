package view.controllers.minigames.oneway;

import java.util.List;

import model.minigames.oneway.Direction;
import utility.Pair;
import view.controllers.Area;

/**
 * The interface for the visual representation of One Way.
 * 
 */
@Area(trainingArea = "Brain Speed")
public interface OneWayFx {

    /**
     * At first initialization, draw the upper grid of the game.
     * @param steps
     *         the steps to turn into arrows
     */
    void drawArrows(List<Direction> steps);

    /**
     * At each level, update the arrows to draw.
     * @param steps
     *          the steps to turn into arrows
     */
    void updateArrows(List<Direction> steps);

    /**
     * At first initialization, draw the main grid for the component of the game.
     * @param size
     *         the size of the grid
     */
    void drawGrid(int size);

    /**
     * Show the initial position on the screen.
     * @param initialPosition
     *                    the initial position
     */
    void updateView(Pair<Integer, Integer> initialPosition);

    /**
     * Show if the player guess the answer.
     * @param finalPosition
     *                  the final position
     */
    void showCorrectAnswer(Pair<Integer, Integer> finalPosition);
}
