package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Interface used to implement Composite pattern.
 */
public interface Component {
    /**
     * Updates Component status.
     * 
     * @param elapsed the time elapsed since last update.
     */
    void update(double elapsed);

    /**
     * Enables the {@link Component}.
     */
    void enable();

    /**
     * Disables the {@link Component}.
     */
    void disable();

    /**
     * Gets the {@link Component}'s type.
     * 
     * @return the tag which defines the {@link ComponentType}.
     */
    ComponentType getType();

    /**
     * Sets the {@link Component}'s parent.
     * 
     * @param parent the {@link GameObject} attached to the {@link Component}.
     */
    void setParent(GameObject parent);

    /**
     * Gets the {@link GameObject} attached.
     * 
     * @return the {@link GameObject} attached to the {@link Component}.
     */
    GameObject getParent();
}
