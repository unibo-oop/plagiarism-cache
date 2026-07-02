package controller.leaderboard;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import controller.utilities.IOLeaderboard;
import model.leaderboard.Leaderboard;
import model.leaderboard.LeaderboardImpl;
import model.leaderboard.LeaderboardSortingStrategy;
import model.leaderboard.Player;

public class LeaderboardControllerImpl implements LeaderboardController {

    private Leaderboard leaderboard;
    private final String filePath;

    public LeaderboardControllerImpl(final String filePath) {
        this.filePath = filePath;
        this.initializeLeaderboard();
    }

    /**
     * Method that allows to initialize correctly leaderboard.
    */
    private void initializeLeaderboard() {
        final var map = IOLeaderboard.readLeaderboard(filePath);
        if (map == null || map.isEmpty()) {
            this.leaderboard = new LeaderboardImpl();
        } else {
            this.leaderboard = new LeaderboardImpl(map);
        }
    }
    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void addPlayerInLeaderBoard(final Player player) {
        this.leaderboard.addPlayer(player.getAlias(), player.getScore());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Map<String, Integer> viewLeaderboard() {
        return Collections.unmodifiableMap(this.leaderboard.getLeaderBoard());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Map<String, Integer> getPodium(final int index, final LeaderboardSortingStrategy ls) {
        this.leaderboard.sortByScore(ls);
        return this.leaderboard.getLeaderBoard()
                               .entrySet()
                               .stream()
                               .limit(index)
                               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void saveSortLeaderboard(final LeaderboardSortingStrategy ls) {
        this.leaderboard.sortByScore(ls);
        IOLeaderboard.printInJsonFormat(this.filePath, this.viewLeaderboard());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void clearLeaderboard() {
        this.leaderboard = new LeaderboardImpl();
        IOLeaderboard.printInJsonFormat(this.filePath, this.viewLeaderboard());
    }

}
