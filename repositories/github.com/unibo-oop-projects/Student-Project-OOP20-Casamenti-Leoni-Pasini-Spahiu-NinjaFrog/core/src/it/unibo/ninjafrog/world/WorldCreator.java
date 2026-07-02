package it.unibo.ninjafrog.world;

/**
 * WorldCreator interface definition. To create the world, call the
 * {@link it.unibo.ninjafrog.world.WorldCreator#createWorld() createWorld()}
 * method.
 */
public interface WorldCreator {
    /**
     * Method to be called in order to create the solid world from the tiled map. It
     * generates {@link it.unibo.ninjafrog.world.NonInteractiveObject
     * NonInteractiveObject} and {@link it.unibo.ninjafrog.world.InteractiveObject
     * InteractiveObject} objects where needed.
     */
    void createWorld();
}
