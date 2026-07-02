package unibo.exiled.model.character.enemy.boss;

import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.enemy.EnemyImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.ElementalType;

/**
 * Extension of the enemy representing a Boss of the game.
 */
public abstract class BossEnemy extends EnemyImpl {
    /**
     * Constructs the boss.
     *
     * @param name    The name of the boss.
     * @param moveSet The move set of the boss.
     * @param type    The elemental type of the boss.
     */
    protected BossEnemy(final String name,
                        final MoveSet moveSet,
                        final ElementalType type) {
        super(name, moveSet, new AttributeFactoryImpl().createBossAttributes(), type);
    }

    @Override
    public final int getDroppedExperience() {
        return ConstantsAndResourceLoader.BOSS_DROPPED_EXPERIENCE;
    }
}
