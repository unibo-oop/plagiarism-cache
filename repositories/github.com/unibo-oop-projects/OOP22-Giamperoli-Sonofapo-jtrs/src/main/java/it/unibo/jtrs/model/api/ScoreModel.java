package it.unibo.jtrs.model.api;

/**
 * An interface modelling the score and level of the game.
 */
public interface ScoreModel {

    /**
     * Returns the current level.
     *
     * @return the current level
     */
    int getLevel();

    /**
     * Returns the game score.
     *
     * @return the score
     */
    int getScore();

    /**
     * Evaluates the game score based on the deleted lines and the current level.
     *
     * @param lines the number of deleted lines
     */
    void evaluate(int lines);

    /**
     * Returns the number of deleted lines.
     * 
     * @return the number of lines
     */
    int getLines();
}
