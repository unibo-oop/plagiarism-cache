package fargoal.model.interactable.pickupable.inside_chest.utility.api;

/**
 * This is an enumeration of the name of the utilities the player can found in a chest. 
 */
public enum UtilityType {
    /**
     * This utility is an enchanted weapon, which increments the skill of the player.
     */
    ENCHANTED_WEAPON("ENCHANTED WEAPON"),
    /**
     * This utility is a healing potion, which increments the hit points when used.
     */
    HEALING_POTION("HEALING POTION"),
    /**
     * This utility is a beacon, which, when pleaced on the ground and near the player, 
     * it protects the player. When the player teleports with a spell it teleport himself 
     * near it.
     */
    BEACON("BEACON"),
    /**
     * This utility carry 100 pieces of gold. 
     */
    MAGIC_SACK("MAGIC SACK"),
    /**
     * This utility is the map of a determined floor level.
     */
    MAP("MAP");

    private final String name;

    /**
     * This is the constructor of the enumeration. It set all the names for the many types of utilities.
     * @param name - it is the name of the utility.
     */
    UtilityType(final String name) {
        this.name = name;
    }

    /**
     * With this method the actual name of the utility is revealed.
     * @return the name of the utility.
     */
    public String getName() {
        return this.name;
    }
}
