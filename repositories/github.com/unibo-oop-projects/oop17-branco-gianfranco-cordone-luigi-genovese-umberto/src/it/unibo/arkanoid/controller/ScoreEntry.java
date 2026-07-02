package it.unibo.arkanoid.controller;

/**
 * 
 * Represents a name-score pair for the score list.
 *
 */
public class ScoreEntry {

    private final String name;
    private final int score;
    /**
     * 
     * @param name
     *            player's name.
     * @param score
     *            player's score.
     */
    public ScoreEntry(final String name, final int score) {
        this.name = name;
        this.score = score;

    }

    /**
     * 
     * @return player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return player's score.
     */
    public int getScore() {
        return score;
    }

}
