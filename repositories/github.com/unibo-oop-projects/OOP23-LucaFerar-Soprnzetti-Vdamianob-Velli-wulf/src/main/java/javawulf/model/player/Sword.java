package javawulf.model.player;

import javawulf.model.Direction;
import javawulf.model.GameElement;
import javawulf.model.Coordinate;

/**
 * Sword is class whose purpose is to deal with all of the attack functions of player,
 * which are all linked to the player character's sword and its statistics.
 */
public interface Sword extends GameElement {

    /**
     * The strength a Sword normally has.
     */
    int NORMAL = 1;
    /**
     * The strength a Sword has when it has a strength boosting Power-Up or
     * a certain item.
     */
    int STRONG = 2;

    /**
     * SwordType defines the current type of sword.
     */
    enum SwordType {
        /**
         * NORMAL is the default type of sword.
         */
        NORMAL,
        /**
         * GREATSWORD is the stronger type of sword that can
         * be obtained thorugh an item.
         */
        GREATSWORD;
    }

    /**
     * Updates the position and direction of the Sword according to the player position
     * and movement delta.
     * 
     * @param playerPosition The current position of player
     * @param playerDirection The direction the player is currently facing
     */
    void move(Coordinate playerPosition, Direction playerDirection);

    /**
     * Activates the collision of the Sword allowing the player to attack.
     */
    void activate();

    /**
     * Deactivates the collsion of the Sword.
     */
    void deactivate();

    /**
     * @return The current strength of the player's sword
     */
    int getSwordStrength();

    /**
     * @return The current durability of the player's sword
     */
    int getDurability();

    /**
     * @param durability the sword changes into
     */
    void setDurability(int durability);

    /**
     * Set the sword strength. It is used both when the sword type is GREATSWORD
     * and when a strength boosting power-up is obtained.
     * 
     * @param strength The strength the sword changes into
     */
    void setSwordStrength(int strength);

    /**
     * @return The current type of the player's sword
     */
    SwordType getSwordType();

    /**
     * Changes the sword's type automatically.
     */
    void changeSwordType();

}
