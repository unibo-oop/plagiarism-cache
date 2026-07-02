package vg.model.entity.dynamicEntity.player;

import vg.model.entity.Entity;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.timedObject.Shield;
import vg.utils.Direction;
import vg.utils.V2D;

public interface Player extends Entity {

    /**
     * Decrement player's life of one unit.
     * If shield has expired, create new shield with half of previous one.
     */
    void decLife();
    /**
     * Increment player's life of one unit but not over maximum value.
     */
    void incLife();

    /**
     * Get current player's life.
     * @return numeric value of player's life
     */
    int getLife();
    /**
     * Enable or change current player speed with new one.
     * @param speed Vector that represent speed
     */
    void enableSpeedUp(V2D speed);
    /**
     * Disable speed improvement if enabled.
     */
    void disableSpeedUp();
    /**
     * Return true if player can shoot.
     * @return true if player can shoot, false if not
     */
    boolean canShoot();
    /**
     * Enable player capability to shoot.
     * */
    void enableShoot();
    /**
     * Disable player capability to shoot if enabled.
     * */
    void disableShoot();

    /**
     * Move player to new position coordinate in congruence to current direction.
     *
     * This method overrides the one of superclass {@link DynamicEntity#move()} but
     * its logic is totally different.
     * The player needs to move depending on direction and the speed (that in player
     * represent only the module of movement).
     *
     */
    void move();

    /**
     * Change player's moving direction.
     * @param direction new direction
     * @param isOnBorder flag if player is on border
     */
    void changeDirection(Direction direction, boolean isOnBorder);

    /**
     * Current player direction.
     * @return Direction
     */
    Direction getDirection();

    /**
     * Get player tail.
     * @return Tail of player
     */
    Tail getTail();

    /**
     * Set new player's shield.
     * @param shield new Shield
     */
    void setShield(Shield shield);

    /**
     * Get current player's shield.
     * @return player shield
     */
    Shield getShield();

    /**
     * Get player speed.
     * @return vector which coordinates are in absolute value
     */
    V2D getSpeed();

}
