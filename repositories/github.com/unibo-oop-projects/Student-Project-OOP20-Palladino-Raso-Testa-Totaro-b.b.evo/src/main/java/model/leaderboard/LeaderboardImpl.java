package model.leaderboard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LeaderboardImpl implements Leaderboard {

    private Map<String, Integer> ranking;

    public LeaderboardImpl() {
        this.ranking = new HashMap<>();
    }

    public LeaderboardImpl(final Map<String, Integer> ranking) {
        this.ranking = ranking;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void sortByScore(final LeaderboardSortingStrategy ls) {
        this.ranking = ls.sortMap(this.ranking);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void addPlayer(final String alias, final int score) {
        this.ranking.put(alias, score);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void removePlayer(final String alias, final int score) {
        if (this.ranking.entrySet()
                       .stream()
                       .filter(e -> e.getKey().equals(alias) && e.getValue() == score)
                       .findAny()
                       .isPresent()) {
            this.ranking.remove(alias, score);

        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Map<String, Integer> getLeaderBoard() {
        return Collections.unmodifiableMap(this.ranking);
    }

    /**
     *  Return a String representing the ranking.
     *  @return a String representing the ranking.
     *
     */
    @Override
    public String toString() {
        return this.ranking.toString();
    }

}
