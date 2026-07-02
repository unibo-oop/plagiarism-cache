package unibo.exiled.model.character.attributes;

/**
 * Represents an attribute of a character in the game.
 * Attributes have a base value, a modifier, and an evaluated value.
 */
public interface Attribute {
    /**
     * States if the attribute is a modifier.
     *
     * @return True if the attribute is a modifier, false otherwise.
     */
    boolean isModifier();

    /**
     * States if the attribute is a value.
     *
     * @return True if the attribute is a value, false otherwise.
     */
    boolean isAdditive();
}
