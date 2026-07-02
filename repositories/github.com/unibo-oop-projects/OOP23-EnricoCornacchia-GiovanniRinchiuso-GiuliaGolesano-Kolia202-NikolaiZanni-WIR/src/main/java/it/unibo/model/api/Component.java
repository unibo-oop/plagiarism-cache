package it.unibo.model.api;


/**
 * Represents a component in the system.
 * A component is an entity that can be updated.
 */
public interface Component {
    /**
     * Return the component.
     * This method should be called to get the state of the component.
     * @return the component type.
     */
    ComponentType getComponent();
}
