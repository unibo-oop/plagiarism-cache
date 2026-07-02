package controller.stagehandler.playerhandler;

import java.util.Collection;

import model.weapon.Weapon;

/**
 * Defines all possibile actions a player can make. Some 
 * actions perform after the user press a specific key
 * of his keyboard. 
 */
public interface PlayerHandler {

    /**
     * Finds, if presents, the mapped key and set its value to true.
     * @param action the action performed by the user
     */
    void activate(PlayerAction action);

    /**
     * Finds, if presents, the mapped Key and set its value to false.
     * @param action the action the user doesn't perform anymore
     */
    void deactivate(PlayerAction action);

    /**
     * Tells the player how to act according to the keys
     * pressed by the user.
     */
    void computeAction();

    /**
     * Add the score passed to the actual player score.
     * @param scorePoint the score obtained by the player
     */
    void addScore(int scorePoint);

    /**
     * Return all projectiles fired by the player.
     * @return all projectiles fired by the player.
     */
    Collection<Weapon.Projectile> getFiredProjectiles();

    /**
     *  Increment levels beaten by the player. 
     */
    void beatLevel();

    /**
     * Identifies all the given input by the user.
     */
    enum PlayerAction {

        /**
         * Identifies the acceleration input of the Playership given by the user.
         */
        ACCELERATE,

        /**
         * Identifies the clockwise rotation input of the Playership given by the user.
         */
        ROTATE_CLOCKWISE,

        /**
         * Identifies the deceleration input of the Playership given by the user.
         */
        DECELERATE,

        /**
         * Identifies the anticlockwise rotation input of the Playership given by the user.
         */
        ROTATE_ANTICLOCKWISE,

        /**
         * Identifies the shoot input of the Playership given by the user.
         */
        SHOOT;
    }

}
