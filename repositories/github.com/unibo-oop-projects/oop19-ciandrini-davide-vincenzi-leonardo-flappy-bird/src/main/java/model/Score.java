package model;

public interface Score {

    /**
     * Set score.
     * @param n New score
     */
    void setScore(int n);

    /**
     * Increase by one the score.
     */
    void incrementScore();

    /**
     *
     * @return the score
     */
    int getScore();
}
