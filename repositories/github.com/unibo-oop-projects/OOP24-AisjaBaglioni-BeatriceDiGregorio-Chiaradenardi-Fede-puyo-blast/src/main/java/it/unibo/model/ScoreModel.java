package it.unibo.model;

import it.unibo.model.interfaces.ScoreModelInterface;

/**
 * The ScoreModel class represents the state and behavior of a score system.
 * It manages the player's score, allowing for retrieval and updating of the
 * score.
 */
public class ScoreModel implements ScoreModelInterface {
    /**
     * The current score of the player, initialized to 0.
     * This value tracks the player's score in the game.
     */
    private int score;

    /**
     * Constructor initializing the score to 0.
     * This sets the initial state where the player's score starts at zero.
     */
    public ScoreModel() {
        this.score = 0;
    }

    /**
     * Gets the current score of the player.
     * 
     * @return the current score of the player.
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score of the player to a specified value.
     * This method allows updating the score directly.
     * 
     * @param score the new score to be set.
     */
    @Override
    public void setScore(int score) {
        this.score = score;
    }
}