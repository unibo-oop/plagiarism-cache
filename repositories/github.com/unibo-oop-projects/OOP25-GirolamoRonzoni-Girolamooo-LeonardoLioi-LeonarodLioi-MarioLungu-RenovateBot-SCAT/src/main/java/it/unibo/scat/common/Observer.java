package it.unibo.scat.common;

/**
 * Functional interface that represents the Observer component.
 */
@FunctionalInterface
public interface Observer {

    /**
     * Synchronizes the observer with the current state of the model.
     */
    void update();
}
