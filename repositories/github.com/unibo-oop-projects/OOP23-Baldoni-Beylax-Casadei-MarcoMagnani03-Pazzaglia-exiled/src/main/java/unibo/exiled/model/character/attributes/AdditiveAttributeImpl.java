package unibo.exiled.model.character.attributes;

/**
 * The record of the AdditiveAttribute, which has only a value.
 *
 * @param value The value of the attribute.
 */
public record AdditiveAttributeImpl(double value) implements AdditiveAttribute {

    @Override
    public boolean isModifier() {
        return false;
    }

    @Override
    public boolean isAdditive() {
        return true;
    }
}
