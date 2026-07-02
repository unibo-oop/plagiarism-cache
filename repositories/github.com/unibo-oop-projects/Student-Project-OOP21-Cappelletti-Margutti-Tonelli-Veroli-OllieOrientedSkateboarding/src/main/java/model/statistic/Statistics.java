package model.statistic;

import java.io.IOException;
import java.util.List;

/**
 * 
 * Interface that identifies the statistics of the game.
 *
 */
public interface Statistics {

    /**
     * Updates difficulty and distance covered. 
     */
    void update();

    /**
     * Increases the number of collected coins by given value.
     * @param value the amount of coins to add.
     */
    void increaseCoin(int value);

    /**
     * Gets the difficulty.
     * @return the difficulty.
     */
    double getDifficulty();

    /**
     * Gets the distance.
     * @return the distance.
     */
    int getDistance();

    /**
     * Gets the distance reached in the last run.
     * @return the distance reached in the last run.
     */
    int getLastDeathDistance();

    /**
     * Gets the record distance.
     * @return the record distance.
     */
    int getRecordDistance();

    /**
     * Gets coins collected during the run.
     * @return coins collected during the run.
     */
    int getGameCoins();

    /**
     * Gets player's total coins.
     * @return total coins.
     */
    int getTotalCoins();

    /**
     * Sets the amount of total coins.
     * @param value the amount of total coins.
     */
    void setTotalCoins(int value);

    /**
     * Writes statistics on a file.
     * @throws IOException if occurs problem during the writing.
     */
    void saveStatisticsOnFile() throws IOException;

    /**
     * Reads statistics from a file.
     * @return a list of read statistics.
     */
    List<String> readStatisticsFromFile();


}
