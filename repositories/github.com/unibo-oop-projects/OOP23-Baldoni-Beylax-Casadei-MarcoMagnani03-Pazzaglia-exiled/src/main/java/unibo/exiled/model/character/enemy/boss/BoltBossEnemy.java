package unibo.exiled.model.character.enemy.boss;

import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.item.utilities.ItemNames;
import unibo.exiled.model.move.factory.MoveSetFactoryImpl;
import unibo.exiled.utilities.ElementalType;

import java.util.Optional;

/**
 * The Bolt Boss class.
 */
public final class BoltBossEnemy extends BossEnemy {
    /**
     * Constructs the boss.
     *
     * @param name The name of the boss.
     */
    public BoltBossEnemy(final String name) {
        super(name, new MoveSetFactoryImpl().boltBossMoves(), ElementalType.BOLT);
    }

    @Override
    public Optional<Item> getHeldItem() {
        return ItemsContainer.getItemByName(ItemNames.BOLT_CRYSTAL.getName());
    }
}
