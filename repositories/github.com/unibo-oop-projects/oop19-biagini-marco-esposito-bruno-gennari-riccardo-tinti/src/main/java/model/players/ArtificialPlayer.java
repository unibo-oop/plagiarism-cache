package model.players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;
import model.intelligence.ArtificialIntelligence;

/**
 * This class implements the artificial player object.
 * It has its own statistical values.
 *
 */
public class ArtificialPlayer extends AbstractPlayer {

    /**
     * Represents the generated Serial Version ID for the artificial player.
     */
    private static final long serialVersionUID = -6800182305987141934L;

    private Map<String, Double> stats;
    private transient ArtificialIntelligence intelligence;

    /**
     * Initializes the basic data for this artificial player.
     * 
     * @param nameAI
     *          the name of the artificial player.
     * @param passwordAI
     *          the password of the artificial player.
     * @param intelligence
     *          the intelligence of the artificial player. 
     */
    public ArtificialPlayer(final String nameAI, final String passwordAI, final ArtificialIntelligence intelligence) {
        super(nameAI, passwordAI);
        initStats();
        this.intelligence = intelligence;
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
     * Returns all the statistical values of this artificial player.
     */
    @Override
    public final Map<String, Double> getStatistics() {
        return Collections.unmodifiableMap(this.stats);
    }

    /**
     * Updates a specific statistical value of this artificial player.
     */
    @Override
    public final void updateStats(final String desc, final Double updatedValue) {
        this.stats.computeIfPresent(desc, (x, y) -> Double.valueOf(updatedValue));
    }

    /**
     * Method that returns the specific intelligence of this artificial player.
     * 
     * @return
     *      Returns the intelligence of the artificial player.
     */
    public final ArtificialIntelligence getArtificialIntelligence() {
        return this.intelligence;
    }

}
