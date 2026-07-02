package powpaw.player.controller.impl;

import powpaw.player.controller.api.StatsController;

/**
 * StatsController implementation. For balance reason every stat have a minimum
 * of 5 and a max of 10.
 * 
 * @author Simone Collorà
 */
public final class StatsControllerImpl implements StatsController {
    private static final int MAXPOINTS = 10;
    private static final int MINPOINTS = 5;
    private static final int PLUSMINUS = 1;

    @Override
    public int increase(final int stat) {
        int stats = stat;
        if (stats < MAXPOINTS) {
            stats += PLUSMINUS;
        }
        return stats;
    }

    @Override
    public int decrease(final int stat) {
        int stats = stat;
        if (stats > MINPOINTS) {
            stats -= PLUSMINUS;
        }
        return stats;
    }

}
