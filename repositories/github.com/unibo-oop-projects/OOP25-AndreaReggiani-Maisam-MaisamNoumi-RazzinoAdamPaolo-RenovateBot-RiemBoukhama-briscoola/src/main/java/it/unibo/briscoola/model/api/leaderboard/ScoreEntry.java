package it.unibo.briscoola.model.api.leaderboard;

/**
 * Represents a single record in the game's leaderboard.
 *
 * <p>
 * This interface defines an immutable entry containing a player's name
 * and the final score they achieved in a Briscola match.
 */
public interface ScoreEntry {

    /**
     * Retrieves the score associated of the entry.
     *
     * @return the score points associated to the Entry
     */
    int getScore();

    /**
     * Retrieves the name of the entry.
     *
     * @return the name associated to the entry
     */
    String getName();
}
