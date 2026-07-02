package unibo.exiled.model.character.attributes;

/**
 * Interface of the combined attribute, which should have a method that evaluates the value * modifier.
 */
public interface CombinedAttribute extends AdditiveAttribute, MultiplierAttribute {
    /**
     * Calculates the value * modifier in a combined attribute.
     *
     * @return A double consisting of the multiplication between the value and the modifier.
     */
    double getEvaluated();
}
