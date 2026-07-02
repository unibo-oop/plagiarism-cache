package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.MultiplierAttributeImpl;
import unibo.exiled.model.character.attributes.CombinedAttributeImpl;
import unibo.exiled.model.character.attributes.AdditiveAttribute;
import unibo.exiled.model.character.attributes.AdditiveAttributeImpl;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.MoveSets;
import unibo.exiled.utilities.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The implementation of a generic character.
 */
public abstract class GameCharacterImpl implements GameCharacter {
    private final String name;

    private final MoveSet moveSet;
    private ElementalType type;

    /**
     * An association between the identifier of the attribute and its values.
     */
    private Map<AttributeIdentifier, Attribute> attributes;
    private boolean isMoving = true;
    private Position position;
    private Direction lastDirection = Direction.SOUTH;
    private Optional<MagicMove> exceedingMagicMove;

    /**
     * The constructor of the GameCharacter.
     *
     * @param attributes The attributes of the character to build.
     * @param name       The name of the character.
     * @param type       The elemental type of the character.
     * @param moveSet    The MoveSet of the character, its moves.
     */
    protected GameCharacterImpl(final String name,
            final MoveSet moveSet,
            final ElementalType type,
            final Map<AttributeIdentifier, Attribute> attributes) {
        this.moveSet = moveSet;
        this.type = type;
        this.attributes = attributes;
        this.name = name;
        this.exceedingMagicMove = Optional.empty();
    }

    @Override
    public final Position getPosition() {
        return this.position;
    }

    @Override
    public final Map<AttributeIdentifier, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public final double getHealth() {
        return ((CombinedAttribute) this.attributes.get(AttributeIdentifier.HEALTH)).getEvaluated();
    }

    @Override
    public final double getHealthCap() {
        return ((AdditiveAttribute) this.attributes.get(AttributeIdentifier.HEALTHCAP)).value();
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final Direction getLastDirection() {
        return this.lastDirection;
    }

    @Override
    public final void setLastDirection(final Direction direction) {
        this.lastDirection = direction;
    }

    @Override
    public final void increaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        this.modifyAttribute(id, modifier, 0);
    }

    @Override
    public final void increaseAttributeValue(final AttributeIdentifier id, final double value) {
        this.modifyAttribute(id, 0, value);
    }

    @Override
    public final void decreaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        this.increaseAttributeModifier(id, -modifier);
    }

    @Override
    public final void decreaseAttributeValue(final AttributeIdentifier id, final double value) {
        this.increaseAttributeValue(id, -value);
    }

    /**
     * Modifies a generic attribute by adding or subtracting a value and/or a
     * modifier.
     *
     * @param id       The attribute to modify.
     * @param modifier The modifier to add or subtract.
     * @param value    The value to add or subtract.
     */
    private void modifyAttribute(final AttributeIdentifier id,
            final double modifier,
            final double value) {
        final Map<AttributeIdentifier, Attribute> attributesCopy = new HashMap<>(this.attributes);
        final Attribute attributeToModify = this.attributes.get(id);
        if (attributeToModify instanceof CombinedAttribute conv) {
            attributesCopy.replace(id, new CombinedAttributeImpl(conv.value() + value,
                    conv.modifier() + modifier));
        } else if (attributeToModify instanceof MultiplierAttributeImpl conv) {
            attributesCopy.replace(id, new MultiplierAttributeImpl(conv.modifier() + modifier));
        } else if (attributeToModify instanceof AdditiveAttributeImpl conv) {
            attributesCopy.replace(id, new AdditiveAttributeImpl(conv.value() + value));
        }
        this.attributes = attributesCopy;
    }

    @Override
    public final void move(final Position position) {
        this.position = position;
    }

    @Override
    public final boolean spriteIsMoving() {
        this.isMoving = !isMoving;
        return !this.isMoving;
    }

    @Override
    public final ElementalType getType() {
        return this.type;
    }

    /**
     * Sets the type of the character. Accessible only by subclasses.
     *
     * @param type The new type of the character.
     */
    protected final void setType(final ElementalType type) {
        this.type = type;
    }

    @Override
    public final MoveSet getMoveSet() {
        return MoveSets.copyOf(this.moveSet);
    }

    @Override
    public final void addMagicMove(final MagicMove newMove) {
        this.moveSet.addMagicMove(newMove);
    }

    @Override
    public final void changeMove(final MagicMove oldMove, final MagicMove newMove) {
        this.moveSet.changeMove(oldMove, newMove);
        this.setExceedingMagicMove(Optional.empty());
    }

    @Override
    public final Optional<MagicMove> getExceedingMagicMove() {
        return this.exceedingMagicMove;
    }

    @Override
    public final void setExceedingMagicMove(final Optional<MagicMove> move) {
        this.exceedingMagicMove = move;
    }
}
