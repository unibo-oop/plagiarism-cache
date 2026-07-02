package powpaw.player.controller;

import powpaw.player.model.api.PlayerStats;
import powpaw.player.model.api.StatsBuilder;

/**
 * this class contain the static stats of every player and the limit. It also
 * have getter for stats and limit
 * and build for stats.
 * 
 * @author Simone Collorà
 */
public final class StaticStats {

    private static PlayerStats statsP1;
    private static PlayerStats statsP2;
    private static final int LIMIT = 22;

    private StaticStats() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Get stats of player 1.
     * 
     * @return stats of player 1.
     */
    public static PlayerStats getStatsP1() {
        return statsP1;
    }

    /**
     * Get limit.
     * 
     * @return limit.
     */
    public static int getLimit() {
        return LIMIT;
    }

    /**
     * Get stats of player 2.
     * 
     * @return stats of player 2.
     */
    public static PlayerStats getStatsP2() {
        return statsP2;
    }

    /**
     * Build stats of player 1.
     * 
     * @param build
     * @return builded player 1 stats.
     */
    public static PlayerStats buildStatsP1(final StatsBuilder build) {
        statsP1 = build.build();
        return statsP1;
    }

    /**
     * Build stats of player 2.
     * 
     * @param build
     * @return builded player 2 stats.
     */
    public static PlayerStats buildStatsP2(final StatsBuilder build) {
        statsP2 = build.build();
        return statsP2;
    }
}
