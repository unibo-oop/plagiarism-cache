package view.statistics;

/**
 * 
 *
 * @param <GlobalStatisticsUIController> the controller of GlobalStatistic view
 */
public interface GlobalStatisticsUI<GlobalStatisticsUIController> extends StatisticUI<GlobalStatisticsUIController> {
/**
 * show the table of the global statistics.
 */
    void showGlobalStatistics();
/**
 * show the table of the player statistics.
 * @param playerName the player name.
 */
    void showPlayerStatistics(String playerName);

}
