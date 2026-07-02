package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Model a malus life power-up.
 */
public class MalusLife extends AbstractPowerupEntity {
    /**
     * Construct a malus life power-up in the world.
     * @param position where to create it.
     */
    public MalusLife(final Position position) {
        super(position,  false);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void powerupEffect(final HeroImpl hero) {
        hero.removeLife();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
    }

}
