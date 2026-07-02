package model.minigames.sizecount;

import java.util.List;

import model.minigames.MiniGameModel;

/**
 * 
 * A model interface for the game size count.
 *
 */
public interface SizeCountModel extends MiniGameModel {

    /**
     * Verify if the answer is correct.
     * 
     * @param answer the answer to check
     * @return true if the answer is correct false otherwise
     */
    boolean isCorrectAnswer(String answer);

    /**
     * Get the random operations that compose the game.
     * 
     * @return the list of {@link IntegerOperation} that compose the game
     */
    List<IntegerOperation> getOperations();

    /**
     * Reset the game and compute new operations.
     */
    void reset();

    /**
     * Get the number of operations that are involved in the game.
     * 
     * @return the number of operations of which the game is made up
     */
    Integer getNumOfOperations();
}
