package unibo.exiled.model.character.attributes;

/**
 * The interface of the MultiplierAttribute, the implementation should be a record.
 */
public interface MultiplierAttribute extends Attribute {
    /**
     * Returns the modifier of the attribute.
     *
     * @return A double modifier that needs to be multiplied to something.
     */
    double modifier();
}
