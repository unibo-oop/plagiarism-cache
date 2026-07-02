package boxhead.model.level;

/**
 * Manages round
 *
 */
public interface Round {
	
    /**
     * 
     * @return current round number
     */
    int getCurrentRound();

    /**
     * 
     * @return true or false if round is active
     */
    boolean isRoundActive();

    /**
     * 
     * @return after pause start timing
     */
    double getStartingTime();

    /**
     * 
     * @return number of zombies to spawn
     */
    int getZombiesToSpawn();
    
    /**
     * Updates rounds
     */
    void update();

}
