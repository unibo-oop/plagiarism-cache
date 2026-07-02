package controller;

/**
 * Interface for the creation and calculation of the score in the game.
 * 
 */

public interface Score {

    /**
     * Checks the input received.
     * 
     * @param floorClimbed What is given as input.
     */
    void scoreUpdater(int floorClimbed);

    /**
     * @return the actual value of the score.
     */
    int getScore();

    /**
     * Increments the score when there is a bonus.
     * 
     * @param bonus The value of the score's bonus.
     */
    void increaseScore(int bonus);

    /**
     * Change moltiplicator value to his minimum.
     * 
     */
    void resetMoltiplicator();
}
