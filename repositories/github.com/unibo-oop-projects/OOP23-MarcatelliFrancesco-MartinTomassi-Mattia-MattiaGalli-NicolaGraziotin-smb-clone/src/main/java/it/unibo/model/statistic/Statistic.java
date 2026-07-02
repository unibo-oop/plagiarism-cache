package it.unibo.model.statistic;
/**
 * The Statistic rappresents the total number of death 
 * and the time record of the player.
 */
public interface Statistic {

    /**
     * Add a death every time the player die.
     */
    void addDeaths();
    /**
     * Update the time record every time the player 
     * makes a better time. 
     * @param time the new time made by the player.
     */
    void updateRecord(int time);
    /**
     * Returns the total number of deaths.
     * 
     * @return total number of deaths.
     */
    int getDeaths();
    /**
     * Returns the best time record.
     * 
     * @return the best time record.
     */
    int getRecordTime();
}
