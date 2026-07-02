package it.unibo.briscoola.model.api.leaderboard;

import java.util.List;

/**
 * This interface provides methods to read from and write to
 * an external storage system (such a JSON file) in a file inside
 * a hidden folder in the user's home, allowing the leaderboard to
 * persist across different game sessions.
 */
public interface ScoreFileManager {

    /**
     *  Saves the given LeaderBoard inside the leaderboard file.
     *
     * @param list lists of entries to save to file
     * @return true if the save was successful, false otherwise
     */
    boolean save(List<ScoreEntry> list);

    /**
     * Retrieves the data saved inside the leaderboard file.
     *
     * @return the list of entries a leaderboard if it exists, an empty leaderboard otherwise
     */
    List<ScoreEntry> load();

    /**
     * Clears every data present inside the leaderboard file.
     *
     * @return true if the leaderboard was cleared successfully, false otherwise
     */
    boolean clearLeaderBoard();
}
