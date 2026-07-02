package javawulf.model.powerup;

import javawulf.model.Coordinate;

/**
 * PowerUpFactory is used to represent the factory for creation of all kinds of powerUp.
 */
public interface PowerUpFactory {

    /**
     * Creates a PowerUp Attack.
     * 
     * @param coordinates Where the PowerUp will be created
     * @return The PowerUp Attack
     */
    PowerUpAttack createPowerUpAttack(Coordinate coordinates);

    /**
     * Creates a PowerUp Double Points.
     * 
     * @param coordinates Where the PowerUp will be created
     * @return The PowerUp Double Points
     */
    PowerUpDoublePoints createPowerUpDoublePoints(Coordinate coordinates);

    /**
     * Creates a PowerUp Invincibility.
     * 
     * @param coordinates Where the PowerUp will be created
     * @return The PowerUp Invincibility
     */
    PowerUpInvincibility createPowerUpInvincibility(Coordinate coordinates);

    /**
     * Creates a PowerUp Speed.
     * 
     * @param coordinates Where the PowerUp will be created
     * @return The PowerUp Speed
     */
    PowerUpSpeed createPowerUpSpeed(Coordinate coordinates);

}
