package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Model a bonus life power-up.
 */
public class BonusLife extends AbstractPowerupEntity {
    /**
     * Construct a bonus life power-up in the world.
     * @param position where to create it.
     */
    public BonusLife(final Position position) {
        super(position,  true);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void powerupEffect(final HeroImpl hero) {
        hero.addLife();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
    }
}
