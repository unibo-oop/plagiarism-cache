package ballblast.controller.files;

import java.util.Optional;

import ballblast.model.data.Leaderboard;

/**
 * The manager for handling the {@link Leaderboard}.
 */
public interface LeaderboardManager {

    /**
     * Loads the survival leaderboard.
     * 
     * @return an {@link Optional} {@link Leaderboard}, if present.
     */
    Optional<Leaderboard> loadSurvivalLeaderboard();

    /**
     * Saves leaderboard data in the xml file.
     * 
     * @param lb the {@link Leaderboard} datas to save.
     * @return true if save operation done successfully, false otherwise.
     */
    boolean saveSurvivalLeaderboard(Leaderboard lb);

}
