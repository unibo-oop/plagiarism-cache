package unibo.exiled.model.character.player;

import java.util.Optional;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.utilities.ElementalType;

/**
 * The interface of the Player.
 */
public interface Player extends GameCharacter {

    //
    //  GETTER
    //

    /**
     * Gets the level of the player.
     *
     * @return the level of the player.
     */
    int getLevel();

    /**
     * Gets the experience of the player.
     *
     * @return the experience points of the player.
     */
    int getExperience();

    /**
     * Gets the experience cap of the player.
     *
     * @return the experience cap of the player.
     */
    int getCapExperience();

    /**
     * Gets the inventory of the player.
     *
     * @return the inventory containing the player's items.
     */
    Inventory getInventory();


    //
    //  SETTER
    //


    /**
     * Sets the elemental type chosen from the player.
     *
     * @param playerClass the class choosen.
     */
    void setPlayerClass(ElementalType playerClass);


    //
    //  OTHERS
    //

    /**
     * Adds an item to the player inventory.
     *
     * @param item the item to be added.
     */
    void addItemToInventory(Item item);

    /**
     * Adds experience, if it exceeds the levelUp cap by increasing statistics.
     *
     * @param exp experience provided to the user.
     */
    void addExperience(int exp);

    /**
     * Uses the specified UsableItem. The effect of the item is applied to the
     * player,
     * and the item is consumed from the player's inventory.
     *
     * @param item The UsableItem to be used.
     */
    void useItem(UsableItem item);

    /**
     * Retrieves a new magic move, if available.
     *
     * @return an Optional containing the new magic move, or empty if no move is available.
     */
    Optional<MagicMove> getNewMove();
}
