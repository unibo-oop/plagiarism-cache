package powpaw.player.model.api;

/**
 * StatsBuilder interface.
 * 
 * @author Simone Collorà
 */
public interface StatsBuilder {

    /**
     * Set attack.
     * 
     * @param attack
     */
    void setAttack(int attack);

    /**
     * Set defence.
     * 
     * @param defence
     */
    void setDefence(int defence);

    /**
     * Set speed.
     * 
     * @param speed
     */
    void setSpeed(int speed);

    /**
     * Build player.
     * 
     * @return buided player
     */
    PlayerStats build();
}
