package model.score;

/**
 * This interface has to model the Score object model.
 */
public interface Score {
    /**
     * Method to Set the score.
     * @param n
     */
    void setScore(int n);

    /**
     * Method to increase score by one.
     */
    void incrementScore();

    /**
     * Method to Get the Score.
     * @return score value
     */
    int getScore();
}
