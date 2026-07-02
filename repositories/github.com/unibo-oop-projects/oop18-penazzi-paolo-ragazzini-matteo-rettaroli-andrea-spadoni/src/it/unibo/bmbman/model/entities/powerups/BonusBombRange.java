package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Position;
/**
 * Model a bonus bomb range power-up.
 */
public class BonusBombRange extends AbstractPowerupEntity {
    private static final int RANGE = 5;
    private static final int INITIAL_RANGE = 3;
    /**
     * Construct a bonus bomb range power-up in the world.
     * @param position where to create it.
     */
    public BonusBombRange(final Position position) {
        super(position, true);
    }
    /**
     * {@inheritDoc}}
     */
    @Override
    public void powerupEffect(final HeroImpl hero) {
        hero.setBombRange(RANGE);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
       hero.setBombRange(INITIAL_RANGE);
    }
}
