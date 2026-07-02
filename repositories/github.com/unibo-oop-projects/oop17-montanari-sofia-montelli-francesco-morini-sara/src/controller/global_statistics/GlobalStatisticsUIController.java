package controller.global_statistics;

import java.util.List;

import model.statistic.Statistic;
/**
 * Interface that represent the controller of Global Statistic UI.
 *  {@link GlobalStatisticsUI}.
 */
public interface GlobalStatisticsUIController {
    /**
     * @return the list of the global statistics.
     */
    List<Statistic<?>> getGlobalStatistics();
    /**
     * @param playerName name of the player.
     * @return the list of the player statistics.
     */
    List<Statistic<?>> getPlayerGlobalStatistics(String playerName);
}
