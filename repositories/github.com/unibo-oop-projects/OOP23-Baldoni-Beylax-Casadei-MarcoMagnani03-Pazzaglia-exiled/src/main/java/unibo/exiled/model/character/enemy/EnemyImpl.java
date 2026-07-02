package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.MoveSets;

import java.util.Map;
import java.util.Optional;

/**
 * Abstract implementation of the Enemy interface.
 */
public abstract class EnemyImpl extends GameCharacterImpl implements Enemy {
    /**
     * Constructs an enemy with a name, move set, and attributes.
     *
     * @param name       The name of the enemy.
     * @param moveSet    The move set of the enemy.
     * @param attributes The attributes of the enemy.
     * @param type       The Elemental Type of the enemy.
     */
    public EnemyImpl(
            final String name,
            final MoveSet moveSet,
            final Map<AttributeIdentifier, Attribute> attributes,
            final ElementalType type
    ) {
        super(name, MoveSets.copyOf(moveSet), type, attributes);
        this.setType(type);
    }

    @Override
    public abstract int getDroppedExperience();

    @Override
    public abstract Optional<Item> getHeldItem();
}
