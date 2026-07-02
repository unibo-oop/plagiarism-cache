package view.controllers.minigames.sizecount;

import java.util.List;

import model.minigames.sizecount.IntegerOperation;
import view.controllers.Area;

/**
 * 
 * A interface view for the game size count.
 *
 */
@Area(trainingArea = "Reasoning")
public interface SizeCountFx {

    /**
     * Draw the view of the minigame.
     * 
     * @param operations operations that compose the minigame
     */
    void draw(List<IntegerOperation> operations);

    /**
     * Set the operations to show.
     * 
     * @param numOperations the number of operations to show
     */
    void setOperations(int numOperations);

    /**
     * Show that the selected answer is wrong.
     */
    void showWrongAnswer();

    /**
     * Show that the selected answer is wrong.
     */
    void showCorrectAnswer();

    /**
     * Show the end game scene.
     * @param score
     *          the score gained
     */
    void showEndGame(int score);

}
