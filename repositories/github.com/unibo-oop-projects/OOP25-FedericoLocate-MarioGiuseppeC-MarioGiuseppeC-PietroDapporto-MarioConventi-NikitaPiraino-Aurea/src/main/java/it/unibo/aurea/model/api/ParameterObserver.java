package it.unibo.aurea.model.api;

/**
 * Interface representing an observer of game parameters.
 * Objects implementing this interface will be notified whenever 
 * a parameter level changes.
 */
@FunctionalInterface
public interface ParameterObserver {
    /**
     * Method called when a parameter's level is updated.
     *
     * @param type the type of the parameter that changed.
     * @param newLevel the updated level of the parameter (0-100).
     */
    void onParameterChanged(ParameterType type, int newLevel);
}
