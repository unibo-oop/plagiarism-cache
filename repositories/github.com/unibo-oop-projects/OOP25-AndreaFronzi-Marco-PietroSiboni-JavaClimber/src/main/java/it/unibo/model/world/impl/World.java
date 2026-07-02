package it.unibo.model.world.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.api.QueueWorld;

/**
 * Class representing the complete game world.
 * It combines an "upper world" (queue of upcoming entities) and a "real world"
 * (active entities).
 */
public class World {

    /**
     * The queue-based world for upcoming entities.
     */
    private final QueueWorld upperWorld;

    /**
     * The active game world.
     */
    private final GameWorld realWorld;

    /**
     * Constructs a new World.
     * 
     * @param upperWorld the queue-based world for upcoming entities
     * @param realWorld  the active game world
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The upperWorld and realWorld are mutable objects, "
            + "the world store the two world")
    public World(final QueueWorld upperWorld, final GameWorld realWorld) {
        this.upperWorld = upperWorld;
        this.realWorld = realWorld;
    }

    /**
     * Returns the upper world.
     * 
     * @return the upper world
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The getter is necessary to access the upper world,"
            + " which is a mutable object, but it is necessary to pass to use it.")
    public QueueWorld getUpperWorld() {
        return this.upperWorld;
    }

    /**
     * Returns the real world.
     * 
     * @return the real world
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The getter is necessary to access the real world, which is"
            + "a mutable object, but it is necessary to pass to use it.")
    public GameWorld getRealWorld() {
        return this.realWorld;
    }
}
