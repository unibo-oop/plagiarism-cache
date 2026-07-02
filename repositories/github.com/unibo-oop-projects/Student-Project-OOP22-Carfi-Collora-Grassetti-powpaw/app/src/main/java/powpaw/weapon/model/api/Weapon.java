package powpaw.weapon.model.api;

import javafx.geometry.Point2D;
import powpaw.player.model.api.PlayerStats;

/**
 * Interface for WeaponImpl class that defines the properties and methods of a
 * weapon object in a game.
 * 
 * @author Giacomo Grassetti
 */

public interface Weapon {

    /**
     * Getter of the hitbox of a weapon.
     * 
     * @return The weapon hitbox (WeaponHitbox)
     */
    WeaponHitbox getHitbox();

    /**
     * Getter of the position of a weapon.
     * 
     * @return The weapon position (Point2D)
     */
    Point2D getPosition();

    /**
     * The update function updates the position and hitbox of a weapont using a fall
     * transition (TransitionImpl).
     */
    void update();

    /**
     * Setter of the attack of a weapon.
     * 
     * @param attack The weapon attack (double)
     */
    void setAttack(double attack);

    /**
     * Getter of the durability of a weapon.
     * 
     * @return The weapon durability (int)
     */
    int getDurability();

    /**
     * Method that decrements the durability of an object by one.
     */
    void decrementDurability();

    /**
     * Method that reset the durability of an object to its maximum value.
     */
    void resetDurability();

    /**
     * Getter for the ID of an object.
     * 
     * @return The weapon id (int)
     */
    int getId();

    /**
     * Method that check id a weapon has been picked or not.
     * 
     * @return True if the weapon has been picked, false otherwise
     */
    boolean isPicked();

    /**
     * Setter for the value isPicked.
     * 
     * @param isPicked boolean that set the property isPicked
     */
    void setPicked(boolean isPicked);

    /**
     * Method that adds the attack value of a weapon to a player's stats and
     * decreases the weapon's
     * durability, resetting the attack value if the durability reaches zero.
     * 
     * @param ps PlayerStats that contains the statistics of the player
     */
    void addAttack(PlayerStats ps);

    /**
     * Setter for the visibility of a shape and updates a boolean flag accordingly.
     * 
     * @param b boolean value indicating whether the object should be visible or
     *          not.
     */
    void setVisible(boolean b);

    /**
     * Method in the Weapon interface that returns a boolean value indicating
     * whether the weapon is currently visible or not.
     * 
     * @return True if weapon is visible, false otherwise
     */
    boolean isVisible();

}
