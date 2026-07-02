package it.unibo.plantsfarm.model.items.api;

import it.unibo.plantsfarm.model.plant.Rarity;

/**
 * Interface representing a generic farm item.
 */
public interface ItemsFarm {

    /**
     * Returns the type of this tool.
     *
     * @return the tool type
     */
    Tooltype getTooltype();

    /**
     * Upgrades the item if it has enough experience.
     * When the experience reaches the required value defined by the MaxExperienceforLevel
     * the item can be upgraded and its experience is reset.
     *
     */
    void upgrade();

    /**
     * Increases the experience of the item when it is used.
     */
    void useItem();

    /**
     * Returns the current level of the item (NOT THE EXPERIENCE THEY ARE NOT THE SAME).
     *
     * @return the current level
     */
    int getLevel();

    /**
     * Returns the current experience of the item.
     *
     * @return the current experience
     */
    int getExperience();

    /**
     * Returns the amount of experience required to reach the next level.
     *
     * @return the experience required for the next level
     */
    int getExperienceForLevel();

    /**
     * Returns the maximum level that this item can reach.
     *
     * @return the maximum level
     */
    int getMaxLevel();

    /**
     * Returns the minimum level of this item.
     *
     * @return the minimum level
     */
    int getMinLevel();

    /**
     * Update the Rarity of item based on the level.
     *
     */
    void updateRarity();

    /**
     * Return the rarity of the item.
     *
     * @return return current item's rarity
     */
    Rarity getRarityItem();

    /**
     * For loading the level of each items.
     *
     * @param level level saved.
     */
    void setLevel(int level);

    /**
     * Returns the base level of the item based on the rarity of the plant.
     *
     * @param plantrarity the rarity of the plant
     * @return the base level for the item
     */
    int getLevelBaseOnRarity(Rarity plantrarity);

    /**
     * Enumeration of all supported tool types.
     */
    enum Tooltype {
        HOE,
        WATERCAN,
        AXE
    }
}
