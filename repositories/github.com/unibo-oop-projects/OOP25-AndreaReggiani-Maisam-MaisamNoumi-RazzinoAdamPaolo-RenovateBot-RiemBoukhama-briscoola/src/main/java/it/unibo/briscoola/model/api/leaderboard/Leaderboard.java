package it.unibo.briscoola.model.api.leaderboard;

import java.util.Collection;
import java.util.List;

/**
 * Represents a collection of the highest scores achieved in the game.
 * The leaderboard is responsible for managing {@link ScoreEntry} objects,
 * ensuring they are stored, retrieved, and potentially sorted by their
 * numerical score values.
 */
public interface Leaderboard {

    /**
     * Adds an entry to the instanced leaderboard if its score is > 0.
     *
     * @param entry new entry to add into the leaderboard
     * @return true if the entry was added successfully, false if the entry score is 0
     * @throws NullPointerException if entry is null
     */
    boolean addEntry(ScoreEntry entry);

    /**
     * Adds all the eligible entries in the list to the leaderboard (with a score >0)
     * and limits the list to a maximum of 10 after sorting it.
     *
     * @param entryCollection a Collection of ScoreEntry
     * @return if at least one entry was added successfully, false otherwise
     * @throws NullPointerException if entryCollection is null
     */
    boolean addEntries(Collection<? extends ScoreEntry> entryCollection);

    /**
     * Retrieves every element inside the leaderboard.
     *
     * @return a list of every entry present inside the leaderboard
     */
    List<ScoreEntry> getEntries();

    /**
     * Saves to file the current leaderboard through the {@link ScoreFileManager}.
     */
    void saveScores();
}
