package powpaw.player.controller.api;

/**
 * StatsController interface to increase or decrease a stat.
 * 
 * @author Simone Collorà
 */
public interface StatsController {

    /**
     * Increase a stat.
     * 
     * @param stat
     * @return the stat increased.
     */
    int increase(int stat);

    /**
     * Decrease Stat.
     * 
     * @param stat
     * @return stat decreased.
     */
    int decrease(int stat);

}
