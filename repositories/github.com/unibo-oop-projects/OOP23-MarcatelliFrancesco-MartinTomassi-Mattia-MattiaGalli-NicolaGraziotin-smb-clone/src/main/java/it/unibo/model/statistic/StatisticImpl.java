package it.unibo.model.statistic;
/**
 * The Statistic rappresents the implementation methods that 
 * update the total number of death 
 * and the time record of the player.
 */
public class StatisticImpl implements Statistic {
    private int deaths;
    private int recordTime; 

    /**
     * Constructs a new Statistic of the player.
     */
    public StatisticImpl() {
        this.deaths = 0;
        this.recordTime = 0;
    }

    /**
     * Add a death every time the player die.
     */
    @Override
    public void addDeaths() {
        this.deaths++;
    }
    /**
     * Update the time record every time the player 
     * makes a better time. 
     */
    @Override
    public void updateRecord(final int time) {
        if (recordTime == 0 || time < this.recordTime) {
            this.recordTime = time;
        }
    }
    /**
     * Returns the total number of deaths.
     * 
     * @return total number of deaths.
     */
    @Override
    public int getDeaths() {
        return this.deaths;
    }
    /**
     * Returns the best time record.
     * 
     * @return the best time record.
     */
    @Override
    public int getRecordTime() {
        return this.recordTime;
    }

}
