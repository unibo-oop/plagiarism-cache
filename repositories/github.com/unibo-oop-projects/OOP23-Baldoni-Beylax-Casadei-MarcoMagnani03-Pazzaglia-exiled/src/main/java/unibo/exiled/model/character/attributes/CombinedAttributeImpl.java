package unibo.exiled.model.character.attributes;

/**
 * The implementation of the combined attribute, which has a value and a modifier of such value.
 */
public final class CombinedAttributeImpl implements CombinedAttribute {

    /**
     * The base value contained in the attribute.
     */
    private final AdditiveAttributeImpl value;
    /**
     * The modifier of the combined attribute.
     */
    private final MultiplierAttributeImpl modifier;

    /**
     * The constructor of the combined attribute.
     *
     * @param value    The value of the attribute.
     * @param modifier The modifier to apply to the value.
     */
    public CombinedAttributeImpl(final double value, final double modifier) {
        this.value = new AdditiveAttributeImpl(value);
        this.modifier = new MultiplierAttributeImpl(modifier);
    }

    @Override
    public double value() {
        return this.value.value();
    }

    @Override
    public double modifier() {
        return this.modifier.modifier();
    }

    @Override
    public double getEvaluated() {
        return this.value.value() * this.modifier.modifier();
    }

    @Override
    public boolean isModifier() {
        return true;
    }

    @Override
    public boolean isAdditive() {
        return true;
    }
}
