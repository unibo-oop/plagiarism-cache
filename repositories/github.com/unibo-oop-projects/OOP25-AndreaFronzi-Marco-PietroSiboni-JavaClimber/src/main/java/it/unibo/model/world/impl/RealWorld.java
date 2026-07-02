package it.unibo.model.world.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.world.api.AbstractGameWorld;
import it.unibo.model.world.api.BoundWorld;

/**
 * Concrete implementation of the active game world.
 * Contains the main character (Alien) and other active game objects.
 */
public class RealWorld extends AbstractGameWorld {

    private final Alien alien;

    /**
     * Constructs a new RealWorld.
     * 
     * @param alien      the player character
     * @param boundWorld the boundary world
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The Alien instance is intentionally shared with the"
            + " world because is part of the real world and needs to be accessed and modified by other components.")
    public RealWorld(final Alien alien, final BoundWorld boundWorld) {
        super(boundWorld);
        this.alien = alien;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "To modify the alien is necessary to get it.")
    @Override
    public Alien getAlien() {
        return this.alien;
    }

}
