package fargoal.model.interactable.pickupable.inside_chest.trap.api;

/**
 * This is an enumeration of the name of the traps the player can found in a chest. 
 */
public enum TrapType {
    /**
     * This is the trap explosion, which damage the player.
     */
    EXPLOSION("EXPLOSION"),
    /**
     * This is the trap pit, which damage the player.
     */
    PIT("PIT"),
    /**
     * This is the trap teleport, which teleport the player in a different position.
     */
    TELEPORT("TELEPORT"),
    /**
     * This is the ceiling trap, which damage the player.
     */
    CEILING_TRAP("CEILING TRAP");

    private final String name;

    /**
     * This is the constructor of the enumeration. It set all the names for the many types of traps.
     * @param name - it is the name of the trap.
     */
    TrapType(final String name) {
        this.name = name;
    }

    /**
     * With this method the actual name of the trap is revealed.
     * @return the name of the trap.
     */
    public String getName() {
        return this.name;
    }
}
