package controller;

import java.io.Serializable;

/**
 * An util class for storaging score.
 */
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * This is the name of player.
     */
    private final String name;
    /**
     * This is the score of player.
     */
    private final int playerScore;

    /**
     * Score class constructor.
     * 
     * @param name
     *                   The name of the player.
     * @param score
     *                   The score of the player.
     */
    public Score(final String name, final int score) {
        this.name = name;
        this.playerScore = score;
    }
    /**
     * 
     * @return Name of the player
     */
    public String getName() {
        return this.name;
    }
    /**
     * 
     * @return The playerScore.
     */
    public int getScore() {
        return this.playerScore;
    }
}
