package it.unibo.aknightstale.models;

import java.util.Map;
import java.util.Set;

public interface Scoreboard {
    /**
     * Get scoreboard values.
     */
    Set<Map.Entry<String, Integer>> getEntries();

    /**
     * Get scoreboard score of a player.
     */
    Integer getScore(String name);

    /**
     * Set scoreboard score for a player.
     */
    void setScore(String name, Integer score);

    /**
     * Delete an entry from the scoreboard.
     *
     * @param name The name of the player to remove.
     */
    void deleteScore(String name);

    /**
     * Clear the scoreboard by deleting all the entries.
     */
    void clear();

    /**
     * Load scoreboard from file.
     */
    void load();

    /**
     * Save scoreboard to file.
     */
    void save();
}
