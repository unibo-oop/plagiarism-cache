package tmw.model.entities;

import java.util.Optional;

import tmw.common.P2d;
import tmw.common.V2d;
import tmw.model.objects.GameObject;

/**
 * This interface represents the base for every object in the game that needs to
 * move and has a position in the GameWorld.
 */
public interface GameEntity extends GameObject {

    /**
     * This enumeration represents the type of GameEntity which are in this game.
     *
     */
    enum GameEntityType {
        /**
         * This value identify the main character of the game.
         */
        MILK,
        /**
         * This value represents the enemy Stella.
         */
        STELLA,
        /**
         * This value represents the enemy Abbraccio.
         */
        ABBRACCIO,
        /**
         * This value represents an enemy bullet, or generally a bullet that damage the
         * main character.
         */
        BULLET,
        /**
         * This value represents the the bullets shoot from the main character.
         */
        PLAYER_BULLET,
        /**
         * This value represents the boss of the game.
         */
        BOSS
    }

    /**
     * Returns a value of {@link GameEntityType} that is used to identify the
     * entity.
     * 
     * @return the GameEntityType of the entity
     */
    GameEntityType getType();

    /**
     * Returns the velocity of the entity.
     * 
     * @return a {@link V2d} vector that represents the velocity of the entity
     */
    V2d getCurrentVel();

    /**
     * Set the vector which represents the velocity of the entity.
     * 
     * @param vel - The {@link V2d} vector which represents the velocity of the
     *            entity
     */
    void setVel(V2d vel);

    /**
     * Get the speed of the entity.
     * 
     * @return the speed of the entity
     */
    double getSpeed();

    /**
     * Set the speed of the entity.
     * 
     * @param newSpeed - the new speed of the entity
     */
    void setSpeed(double newSpeed);

    /**
     * Returns true if the entity is alive.
     * 
     * @return true if the health of the entity is higher than zero, false otherwise
     */
    boolean isAlive();

    /**
     * Get the health of the entity in that moment.
     * 
     * @return the health of the entity
     */
    int getCurrentHealth();

    /**
     * Set the health of the entity.
     * 
     * @param hp - the amount to set in the entity
     */
    void setHp(int hp);

    /**
     * Reduce the health of the entity.
     * 
     * @param damage - the amount of damage taken by the entity
     */
    void takeDamage(int damage);

    /**
     * Calling this method kills the entity.
     * 
     */
    void destroy();

    /**
     * Update the entity.
     * 
     * @param newPosition - Optional of {@link P2d} which is the new position for
     *                    the entity, if it is empty the entity didn't move
     */
    void update(Optional<P2d> newPosition);

    /**
     * When this method is called the entity shoot and update his information
     * because if this event.
     */
    void shoot();

    /**
     * Return true if the entity is ready to shoot.
     * 
     * @return true if the entity is ready for shooting, false otherwise
     */
    boolean readyToShoot();

}
