package starcatraz.controller;

import starcatraz.model.Statistics;

/**
 * Controller for Statistics.
 */
public interface StatisticsController {

    /**
     * Close statistics of app.
     * @param statistics
     */
    void closeStatisticsButtonClick();

    /**
     * Set statistics of app.
     * @param statistics
     */
    void setStatistics(Statistics statistics);
}
