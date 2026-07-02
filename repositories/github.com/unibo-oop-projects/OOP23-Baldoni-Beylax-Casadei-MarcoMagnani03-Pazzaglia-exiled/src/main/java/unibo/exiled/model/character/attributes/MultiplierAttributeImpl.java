package unibo.exiled.model.character.attributes;

/**
 * The record of the MultiplierAttribute, which has only a modifier.
 *
 * @param modifier The modifier of the attribute.
 */
public record MultiplierAttributeImpl(double modifier) implements MultiplierAttribute {
    @Override
    public boolean isModifier() {
        return true;
    }

    @Override
    public boolean isAdditive() {
        return false;
    }
}
