package unibo.exiled.model.character.enemy.boss;

import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.item.utilities.ItemNames;
import unibo.exiled.model.move.factory.MoveSetFactoryImpl;
import unibo.exiled.utilities.ElementalType;

import java.util.Optional;

/**
 * The grass boss enemy.
 */
public final class GrassBossEnemy extends BossEnemy {
    /**
     * Constructs the boss.
     *
     * @param name The name of the boss.
     */
    public GrassBossEnemy(final String name) {
        super(name, new MoveSetFactoryImpl().grassBossMoves(), ElementalType.GRASS);
    }

    @Override
    public Optional<Item> getHeldItem() {
        return ItemsContainer.getItemByName(ItemNames.GRASS_CRYSTAL.getName());
    }
}
