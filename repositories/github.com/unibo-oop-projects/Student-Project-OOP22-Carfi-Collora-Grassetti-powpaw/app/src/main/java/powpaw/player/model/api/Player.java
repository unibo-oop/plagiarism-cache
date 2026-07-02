package powpaw.player.model.api;

import java.time.Duration;
import java.util.Optional;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import powpaw.player.model.impl.PlayerImpl.PlayerState;
import powpaw.weapon.model.api.Weapon;

/**
 * {@code Player} interface that represents a player object in game.
 * 
 * It contains information about the player's state, position, direction,
 * health, and hitbox.
 * 
 * @author Alessia Carfì, Giacomo Grassetti, Simone Collorà
 */

public interface Player {

    /**
     * Return optional of a weapon.
     * 
     * @return optional of a weapon
     */
    Optional<Weapon> getWeapon();

    /**
     * Set weapon if present.
     * 
     * @param weapon
     */
    void setWeapon(Optional<Weapon> weapon);

    /**
     * Gets the player's assigned number.
     * 
     * @return int The number assigned to the player.
     */
    int getNumber();

    /**
     * Gets the current position of the player.
     * 
     * @return The current position of the player.
     */
    Point2D getPosition();

    /**
     * Returns the current direction of the player.
     *
     * @return the current direction of the player.
     */
    Point2D getDirection();

    /**
     * Sets the current direction of the player.
     *
     * @param direction the direction to set for the player.
     */
    void setDirection(Point2D direction);

    /**
     * Get width of the player.
     * 
     * @return the player's width.
     */
    double getWidth();

    /**
     * Get height of the player.
     * 
     * @return the player's height.
     */
    double getHeight();

    /**
     * Returns the hitbox of the player.
     *
     * @return the hitbox of the player.
     */
    Hitbox getHitbox();

    /**
     * Getter for the hitbox shape of an arm as a rectangle.
     * 
     * @return The shape of ArmHitbox
     */
    Rectangle getArmHitbox();

    /**
     * Increase Arm Hitbox.
     */
    void increaseArmHitbox();

    /**
     * Reduce Arm hitbox.
     */
    void reduceArmHitbox();

    /**
     * Getter of the current state of the player.
     * 
     * @return The current state of the player (PlayerState)
     */
    PlayerState getState();

    /**
     * Set PlayerState.
     * 
     * @param state
     */
    void setCurrentState(PlayerState state);

    /**
     * Get Player direction state.
     * 
     * @return Player direction state
     */
    PlayerState getDirectionState();

    /**
     * Return PlayerStats.
     * 
     * @return PlayerStats.
     * @author Simone Collorà
     */
    PlayerStats getPlayerStats();

    /**
     * Return current Damage.
     * 
     * @return Current Damage.
     * @author Simone Collorà
     */
    DamageMeter getCurrentHealth();

    /**
     * Sets the width of the player.
     *
     * @param width the width to set for the player.
     */
    void setWidth(double width);

    /**
     * Sets the height of the player.
     *
     * @param height the height to set for the player.
     */
    void setHeight(double height);

    /**
     * Sets the jumping state of the player.
     *
     * @param b the jumping state of the player.
     */
    void setIsJumping(boolean b);

    /**
     * Sets the moving right state of the player.
     *
     * @param b the moving right state of the player.
     */
    void setIsMovingRight(boolean b);

    /**
     * Sets the moving left state of the player.
     *
     * @param b the moving left state of the player.
     */
    void setIsMovingLeft(boolean b);

    /**
     * Sets the attacking state of the player.
     *
     * @param b the attacking state of the player.
     */
    void setIsAttacking(boolean b);

    /**
     * Sets the hit state of the player.
     *
     * @param b the hit state of the player.
     */
    void setIsHit(boolean b);

    /**
     * Sets the dodging state of the player.
     *
     * @param b the dodging state of the player.
     */
    void setIsDodging(boolean b);

    /**
     * Makes the player idle.
     * 
     * Set direction to zero.
     */
    void idle();

    /**
     * Method that checks if the player is falling by verifying if their feet are in
     * contact with the terrain.
     * 
     * @return True if the feet player don't intersect the terrain, false otherwise
     */
    boolean isFalling();

    /**
     * Method that sets the direction knockback for a player hitted.
     * 
     * @param direction Point2D with the direction to apply the knockback.
     */
    void setDirectionDeath(Point2D direction);

    /**
     * Method that updates the player's state to HIT, sets the direction of the
     * attack, reduces the
     * player's health by the damage received, and increases the player's knockback.
     * 
     * @param direction The direction from which the attack is coming, represented
     *                  as a Point2D.
     * @param damage    The amount of damage the player receives from the attack.
     */
    void receiveAttack(Point2D direction, double damage);

    /**
     * Updates the state of the player based on the given duration of time passed
     * since the last update.
     * Calls corresponding methods based on the player's current state.
     * Calculates the player's new position based on their current direction, speed,
     * and gravity.
     * 
     * @param deltaTime the duration of time passed since the last update.
     */
    void update(Duration deltaTime);
}
