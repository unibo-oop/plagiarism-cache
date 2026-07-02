package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Position;
/**
 * Model a malus invert power-up.
 */
public class MalusInvert extends AbstractPowerupEntity {
    /**
     * Construct a malus invert power-up in the world.
     * @param position where to create it.
     */
    public MalusInvert(final Position position) {
        super(position,  false);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void powerupEffect(final HeroImpl hero) {
        hero.setVelocityModifier(-1.0);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
        hero.setVelocityModifier(1.0);
    }
}
