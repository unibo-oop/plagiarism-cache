package it.unibo.plantsfarm.model.player.api;

import java.awt.Rectangle;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.plant.Rarity;
import it.unibo.plantsfarm.model.player.PlayersTypes;

/**
 * Base class representing a generic player entity.
 * It stores the player position, movement speed and direction.
 */
public interface Player {

    /**
     * Updates the position of the player based on the elapsed time
     * and the current movement direction.
     *
     * @param time the elapsed time since the last update, in milliseconds
     */
    void updatePlayer(long time);

    /**
     * Sets the current movement direction of the player.
     *
     * @param direction the new movement direction
     */
    void setDirection(UserInput direction);

    /**
     * Returns the current X position of the player.
     *
     * @return the X coordinate in world space
     */
    double getPosx();

    /**
     * Returns the current Y position of the player.
     *
     * @return the Y coordinate in world space
     */
    double getPosy();

    /**
     * Return the next possible position for the player
     * on the X world.
     *
     * @return the next position player on X world.
     */
    double getNextPosX();

    /**
     * Return the next possible position for the player on the Y world.
     *
     * @return the next position player on Y world.
     */
    double getNextPosY();

    /**
     * Returns the current movement direction of the player.
     *
     * @return the last direction set for the player
     */
    UserInput getDirection();

    /**
     * Returns a snapshot of the player's inventory.
     *
     * @return a ModelInventario object representing the player's inventory
     */
    ModelInventario getInventory();

    /**
     * Returns the player's hitbox used for collision detection.
     *
     * @return the current hitbox in world coordinates
     */
    Rectangle getHitBox();

    /**
     * Function for move the player to the next calculated position
     * if the postion is valid.
     */
    void applyMovement();

    /**
     * Return the type of player created.
     *
     * @return the type player
     */
    PlayersTypes getPlayerType();

    /**
     * Uses an item from the player's inventory based on the specified tool type and plant rarity.
     *
     * @param tool the type of tool to be used
     * @param plant the rarity of the plant associated with the item to be used
     */
    void useItem(Tooltype tool, Rarity plant);

    /**
     * Upgrade the item {@code tool}, if has the right experience level.
     *
     * @param tool  tool in input.
     */
    void upgradeItemRarityFromPlayer(Tooltype tool);
}
