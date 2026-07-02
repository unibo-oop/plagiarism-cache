package it.unibo.oop.manpac.model.score;

/**
 * Represent the actual and record score of player.
 * 
 * @param <X> Type of points. The use of an immutable type (eg Integer, String,
 *            etc.) is highly recommended.<br>
 *            The type MUST extend java.io.Serializable.
 */
public interface ScorePoints<X extends java.io.Serializable> extends java.io.Serializable {

    /**
     * To get a copy of high score.
     * 
     * @return Return the copy of high score of type {@link Points}
     */
    Points<X> getHighScore();

    /**
     * To get a copy of current score.
     * 
     * @return Return the copy of current score of type {@link Points}
     */
    Points<X> getCurrentScore();

    /**
     * To get a copy of {@link ScorePoints}.
     * 
     * @return Return the copy of ScorePoints of type {@link ScorePoints}
     */
    ScorePoints<X> getCopy();

    /**
     * Set the high score to value of parameter (for example for loading the high
     * score).
     * 
     * @param points {@link Points} to set the highest score.
     */
    void setHighScore(Points<X> points);

    /**
     * Increase/decrease the current score of points.
     * 
     * @param points {@link Points} to add in current score (points can be negative:
     *               in this case the score will be decreased)
     */
    void increaseCurrentScore(Points<X> points);

    /**
     * Reset the current score.
     */
    void resetCurrentScore();

    /**
     * Reset the high score and current score.
     */
    void resetScorePoints();

    /**
     * Set the current score to high score.
     */
    void newHighScore();
}
