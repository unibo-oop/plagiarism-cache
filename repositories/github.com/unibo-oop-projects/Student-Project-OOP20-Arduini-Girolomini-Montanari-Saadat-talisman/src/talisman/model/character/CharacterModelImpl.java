package talisman.model.character;

import talisman.model.character.defaultCharacters.CharacterType;

public class CharacterModelImpl implements CharacterModel {

    private int health;
    private int strength;
    private int craft;
    private int gold;
    private int fate;

    private final CharacterType type;
    private final InventoryModel inventory = new InventoryModelImpl();

    /**
     * Creates the character's informations.
     *
     * @param health - the initial health value
     * @param strength - the initial strength value
     * @param craft - the initial craft value
     * @param gold - the initial gold coin number
     * @param fate - the initial fate coin number
     */
    public CharacterModelImpl(final int health, final int strength, final int craft, final int gold, final int fate, final CharacterType type) {

        this.health = health;
        this.strength = strength;
        this.craft = craft;
        this.gold = gold;
        this.fate = fate;
        this.type = type;
    }

    /**
     * Gets the number of health points.
     *
     * @return the value
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the number of strength points.
     *
     * @return the value
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Gets the number of craft points.
     *
     * @return the value
     */
    public int getCraft() {
        return this.craft;
    }

    /**
     * Gets the number of fate coins.
     *
     * @return the value
     */
    public int getFate() {
        return this.fate;
    }

    /**
     * Gets the number of gold coins.
     *
     * @return the value
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Sets health points value.
     *
     @param points - the value to be set
     */
    public void setHealth(final int points) {
        this.health = points;
    }

    /**
     * Sets strength points value.
     *
     @param points - the value to be set
     */
    public void setStrength(final int points) {
        this.strength = points;
    }

    /**
     * Sets craft points value.
     *
     @param points - the value to be set
     */
    public void setCraft(final int points) {
        this.craft = points;
    }

    /**
     * Sets Fate points value.
     *
     @param coins - the value to be set
     */
    public void setFate(final int coins) {
        this.fate = coins;
    }

    /**
     * Sets gold coins number.
     *
     @param coins - the value to be set
     */
    public void setGold(final int coins) {
        this.gold = coins;
    }

    /**
     * Returns the instance of the character inventory
     *
     * @return the inventory
     */
    public InventoryModel getInventory() {

        return inventory;
    }

    /**
     * Returns the type of the base character
     *
     * @return the character
     */
    public CharacterType getType() {

        return type;
    }
}
