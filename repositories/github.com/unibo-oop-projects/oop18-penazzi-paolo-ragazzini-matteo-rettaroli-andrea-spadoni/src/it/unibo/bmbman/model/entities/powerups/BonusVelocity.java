package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Model a bonus velocity power-up.
 */
public class BonusVelocity extends AbstractPowerupEntity {
    /**
     * Construct a bonus velocity power-up in the world.
     * @param position where to create it.
     */
    public BonusVelocity(final Position position) {
        super(position,  true);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void powerupEffect(final HeroImpl hero) {
        hero.setVelocityModifier(2.0);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
        hero.setVelocityModifier(1.0);
    }
}
