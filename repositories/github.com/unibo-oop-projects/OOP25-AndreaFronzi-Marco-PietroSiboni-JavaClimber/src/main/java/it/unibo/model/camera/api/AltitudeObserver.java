package it.unibo.model.camera.api;

/**
 * Interface for objects that need to be notified about altitude changes.
 */
@FunctionalInterface
public interface AltitudeObserver {

    /**
     * Update the observer with the vertical displacement.
     * 
     * @param delta the delta of vertical distance that the player has climbed
     */
    void update(double delta);
}
