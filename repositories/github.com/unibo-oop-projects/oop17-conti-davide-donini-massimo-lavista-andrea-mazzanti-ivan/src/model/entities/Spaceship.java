package model.entities;

import model.entities.properties.DirectionX;
import model.entities.properties.DirectionY;

import org.apache.commons.lang3.tuple.Pair;

import model.entities.powerup.PowerUpType;

/**
 * Represent the player.
 */

public interface Spaceship extends Character, Shooter {

    /**
     * @param powerUp
     *          the power up that develop the spaceship.
     */
    void pickUpPowerUp(PowerUpType powerUp);

    /**
     * 
     * @return true if the shield is active, otherwise false.
     */
    boolean isActiveShield();

    /**
     * 
     * @param fire
     *          indicates if the spaceship can fire.
     */
    void setFire(boolean fire);

    /**
     * 
     * @return true if the spaceship is firing, false otherwise.
     */
    boolean isFiring();

    /**
     * 
     * @return the damage of spaceship.
     */
    double getSpaceshipDamage();

    /**
     * 
     * @return the bullet fired in a minute.
     */
    int getBulletFiredForMinute();

    /**
     * 
     * @return the direction.
     */
    Pair<DirectionX, DirectionY> getDirection();

    /**
     * Set the direction of the spaceship on the X axis.
     * 
     * @param directionX
     *            velocity axis X
     */
    void setDirectionX(DirectionX directionX);

    /**
     * Set the direction of the spaceship on the Y axis.
     * 
     * @param directionY
     *            velocity axis Y
     */
    void setDirectionY(DirectionY directionY);

}
