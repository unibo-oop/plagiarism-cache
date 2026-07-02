package unibo.exiled.model.character.enemy.boss;

import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.item.utilities.ItemNames;
import unibo.exiled.model.move.factory.MoveSetFactoryImpl;
import unibo.exiled.utilities.ElementalType;

import java.util.Optional;

/**
 * The water boss enemy.
 */
public final class WaterBossEnemy extends BossEnemy {
    /**
     * Constructs the boss.
     *
     * @param name The name of the boss.
     */
    public WaterBossEnemy(final String name) {
        super(name, new MoveSetFactoryImpl().waterBossMoves(), ElementalType.WATER);
    }

    @Override
    public Optional<Item> getHeldItem() {
        return ItemsContainer.getItemByName(ItemNames.WATER_CRYSTAL.getName());
    }
}
