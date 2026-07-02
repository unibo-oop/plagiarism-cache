package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.controller.SoundsController;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Position;
/**
 * Model the key to find to finish the game.
 */
public class Key extends AbstractPowerupEntity {
    /**
     * Construct the key in the world.
     * @param position where to create it.
     */
    public Key(final Position position) {
        super(position, true);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void powerupEffect(final HeroImpl hero) {
        hero.setKey();
        SoundsController.getKeySound().ifPresent(s -> s.play());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
    }

}
