package it.unibo.jtrs.controller.api;

/**
 * An interface modelling the score controller. It make possible to know the
 * current level, score, the number of last line removed and makes possible to
 * update the score accordingly to game rules.
 */
public interface ScoreController {

    /**
     * Returns the current level.
     *
     * @return the current level
     */
    int getLevel();

    /**
     * Returns the score.
     *
     * @return the score
     */
    int getScore();

    /**
     * Evaluates a new score based on the number of lines completed.
     *
     * @param lines the number of lines
     */
    void evaluate(int lines);

    /**
     * Returns the number of deleted rows.
     *
     * @return the number of rows
     */
    int returnRemoved();

}
