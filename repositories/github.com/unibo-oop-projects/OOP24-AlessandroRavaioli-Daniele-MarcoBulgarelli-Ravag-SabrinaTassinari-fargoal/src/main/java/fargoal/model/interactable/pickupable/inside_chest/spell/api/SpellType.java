package fargoal.model.interactable.pickupable.inside_chest.spell.api;

/**
 * This is an enumeration of the spells the player can found in a chest. 
 */
public enum SpellType {
    /**
     * This is the shield spell, it protects the player when he is damaged.
     */
    SHIELD("SHIELD SPELL"),
    /**
     * This is the invisibility spell, it make invisible the player.
     */
    INVISIBILITY("INVISIBILITY SPELL"),
    /**
     * This is the light spell, it lights the darkness near the player.
     */
    LIGHT("LIGHT SPELL"),
    /**
     * This is the drift spell, it protects the player when he falls in a pit.
     */
    DRIFT("DRIFT SPELL"),
    /**
     * This is the teleport spell, it teleport the player.
     */
    TELEPORT("TELEPORT SPELL"),
    /**
     * This is the regeneration spell, it regenerate the player's hit points faster.
     */
    REGENERATION("REGENERATION SPELL");

    private final String name;

    /**
     * This is the constructor of the enumeration.
     * @param name - it is the name of the spell.
     */
    SpellType(final String name) {
        this.name = name;
    }

    /**
     * With this method, the actual name of the spell can be revealed.
     * @return the name of the spell.
     */
    public String getName() {
        return this.name;
    }
}
