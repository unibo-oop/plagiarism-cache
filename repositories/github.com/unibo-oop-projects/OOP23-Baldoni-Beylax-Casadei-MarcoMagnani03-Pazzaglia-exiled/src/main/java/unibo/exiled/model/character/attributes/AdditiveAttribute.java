package unibo.exiled.model.character.attributes;

/**
 * The interface of the AdditiveAttribute, the implementation should be a record.
 */
public interface AdditiveAttribute extends Attribute {
    /**
     * Gets the value of an additive attribute.
     *
     * @return The double value of the attribute that needs to be added to something else.
     */
    double value();
}
