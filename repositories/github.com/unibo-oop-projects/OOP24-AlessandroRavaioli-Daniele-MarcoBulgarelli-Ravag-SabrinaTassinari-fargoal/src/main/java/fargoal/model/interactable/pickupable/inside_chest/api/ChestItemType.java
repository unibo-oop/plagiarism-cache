package fargoal.model.interactable.pickupable.inside_chest.api;

/**
 * This is an enumeration of the types the player can found in a chest. 
 * Inside he can found traps, spells or utilities.
 */
public enum ChestItemType {
    /**
     * The chest item trap, it damages the player.
     */
    TRAP("TRAP"),
    /**
     * The chest item spell, it helps the player.
     */
    SPELL("SPELL"),
    /**
     * The chest item utility, it helps the player.
     */
    UTILITY("UTILITY");

    private final String name;

    /**
     * This is the constructor of the enumeration. It set all the names for the chest items types.
     * @param name - it is the name of the chest item type.
     */
    ChestItemType(final String name) {
        this.name = name;
    }

    /**
     * With this method, the actual name of the type of the chest item can be revealed.
     * @return the name of the chest item's type.
     */
    public String getName() {
        return this.name;
    }
}
