package it.unibo.scat.model.leaderboard;

import java.util.List;

import it.unibo.scat.common.GameRecord;

/**
 * Interface that defines the operations for managing the game's leaderboard.
 * It provides methods for data persistence, record management, and ranking
 * logic.
 */
public interface LeaderboardInterface {

    /**
     * Initializes the leaderboard by loading existing data or creating default
     * records.
     */
    void initLeaderboard();

    /**
     * Updates the persistent storage with the current list of game records.
     */
    void updateFile();

    /**
     * Adds a new game record to the leaderboard and ensures it is persisted and
     * sorted.
     * 
     * @param username the username.
     * @param level    the current level.
     * @param score    the current score.
     */
    void addNewGameRecord(String username, int level, int score);

    /**
     * Returns a read-only list of all game records.
     * 
     * @return a list of current leaderboard entries.
     */
    List<GameRecord> getAllRecords();

    /**
     * Sorts the game records by score, then by level, then by date.
     */
    void sortGames();
}
