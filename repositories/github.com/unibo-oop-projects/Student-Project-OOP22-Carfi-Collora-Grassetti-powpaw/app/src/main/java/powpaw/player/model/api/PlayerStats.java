package powpaw.player.model.api;

/**
 * PlayerStats model with getter and setter.
 * 
 * @author Simone Collorà
 */
public interface PlayerStats {

    /**
     * Get attack.
     * 
     * @return attack
     */
    double getAttack();

    /**
     * Get defence.
     * 
     * @return defence
     */
    double getDefence();

    /**
     * Get speed.
     * 
     * @return speed.
     */
    double getSpeed();

    /**
     * Set attack.
     * 
     * @param attack
     */
    void setAttack(double attack);

    /**
     * Set defence.
     * 
     * @param defence
     */
    void setDefence(double defence);

    /**
     * Set speed.
     * 
     * @param speed
     */
    void setSpeed(double speed);

}
