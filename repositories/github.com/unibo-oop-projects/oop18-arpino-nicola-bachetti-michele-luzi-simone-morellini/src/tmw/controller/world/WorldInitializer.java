package tmw.controller.world;

import tmw.model.world.GameWorld;

/**
 * This class represents the Context in which strategy should be applied.
 * 
 * @version 1.0
 */

public class WorldInitializer {

    private final WorldDispenser populator;
    /**
     * Public constructor.
     * 
     * @param populator {@link WorldDispenser} dispenser with something to load
     */
    public WorldInitializer(final WorldDispenser populator) {

        this.populator = populator;
    }

    /**
     * Execute a specific strategy by putting in world
     * entities present in dispenser.
     * 
     * @param world {@link GameWorld} world in which load entities
     */
    public void execute(final GameWorld world) {
        this.populator.populateWorld(world);
    }

}

