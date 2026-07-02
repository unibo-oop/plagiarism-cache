package model.players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;

/**
 * The class implements the human player object. 
 * It has its own statistic values.
 * 
 */
public class HumanPlayer extends AbstractPlayer {

    /**
     * Represents the default Serial Version ID for the human player.
     */
    private static final long serialVersionUID = 1L;

    private Map<String, Double> stats;

    /**
     * Initializes the basic data for this human player.
     * 
     * @param name
     *          the name of the human player.
     * @param password
     *          the password of the human player.
     */
    public HumanPlayer(final String name, final String password) {
        super(name, password);
        initStats();
    }

    /**
     * Initializes all the basic statistical values.
     */
    private void initStats() {
        this.stats = new HashMap<>();
        Arrays.asList(StatsInfo.values()).forEach(x -> {
            this.stats.put(x.getName(), AbstractPlayer.DEF_INIT_STATS_VAL);
        });
    }

    /**
     * Updates a specific statistical value of this human player.
     */
    @Override
    public final void updateStats(final String desc, final Double updatedValue) {
        this.stats.computeIfPresent(desc, (x, y) -> Double.valueOf(updatedValue));
    }

    /**
     * Returns all the statistical values of this human player.
     */
    @Override
    public final Map<String, Double> getStatistics() {
        return Collections.unmodifiableMap(this.stats);
    }

}
