package model;
/**
 * Define a model for the statistics
 * @author lucadalseno
 *
 */
public interface StatisticModel {
    /**
     * Add a statistic to a player
     * @param p: the player
     * @param s: the statistic to add
     */
    void addStatistic(Player p,Statistics s);
    
    /**
     * Get all the statistics for a player
     * @param p: the player
     * @return a Statistics object which contains all 
     * the statistics
     */
    Statistics getStatistic(Player p);
    
    /**
     * Apply the statistics to the model
     */
    void applyStatistic();
}
