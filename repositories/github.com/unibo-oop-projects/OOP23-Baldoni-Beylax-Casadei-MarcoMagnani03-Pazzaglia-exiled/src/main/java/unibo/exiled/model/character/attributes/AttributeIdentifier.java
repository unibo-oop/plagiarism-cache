package unibo.exiled.model.character.attributes;

/**
 * An enum containing every used attribute and their names.
 */
public enum AttributeIdentifier {
    /**
     * The attack modifier of a character. is Modifier.
     */
    ATTACK("Attack"),
    /**
     * How a character resists to damage. is Modifier.
     */
    DEFENSE("Defense"),
    /**
     * The health of a character. is Combined.
     */
    HEALTH("Health"),
    /**
     * The maximum health a character can have. is Additive.
     */
    HEALTHCAP("Health Cap");

    private final String name;

    /**
     * The constructor of the attribute identifier.
     *
     * @param name The name of the attribute.
     */
    AttributeIdentifier(final String name) {
        this.name = name;
    }

    /**
     * Gets the name of the attribute to be viewed.
     *
     * @return A String representing the name of the attribute.
     */
    public String getName() {
        return this.name;
    }
}
