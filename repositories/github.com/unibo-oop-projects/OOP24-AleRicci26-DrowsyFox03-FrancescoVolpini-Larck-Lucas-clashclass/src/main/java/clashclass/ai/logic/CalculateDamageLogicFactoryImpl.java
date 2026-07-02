package clashclass.ai.logic;

import clashclass.ecs.GameObject;
import clashclass.stats.DefenseBuildingBaseStatsComponent;
import clashclass.stats.TroopBaseStatsComponent;

/**
 * Represents a {@link CalculateDamageLogic} implementation.
 */
public class CalculateDamageLogicFactoryImpl implements CalculateDamageLogicFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public DamageLogicComponent createForBarbarian() {
        return new DamageLogicComponent(new DefaultTroopsDamageLogic());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DamageLogicComponent createForArcher() {
        return new DamageLogicComponent(new DefaultTroopsDamageLogic());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DamageLogicComponent createForCannon() {
        return new DamageLogicComponent(new DefaultDefensesDamageLogic());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DamageLogicComponent createForArcherTower() {
        return new DamageLogicComponent(new DefaultDefensesDamageLogic());
    }

    /**
     * Represents a default damage logic for troops.
     */
    private static final class DefaultTroopsDamageLogic implements CalculateDamageLogic {
        /**
         * {@inheritDoc}
         */
        @Override
        public int calculateDamage(final GameObject actor, final GameObject target) {
            return actor.getComponentOfType(TroopBaseStatsComponent.class).get().getDamage();
        }
    }

    /**
     * Represents a default damage logic for defenses.
     */
    private static final class DefaultDefensesDamageLogic implements CalculateDamageLogic {
        /**
         * {@inheritDoc}
         */
        @Override
        public int calculateDamage(final GameObject actor, final GameObject target) {
            return actor.getComponentOfType(DefenseBuildingBaseStatsComponent.class).get().getDamage();
        }
    }
}
