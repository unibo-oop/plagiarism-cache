package clashclass.ecs;

/**
 * Represents a Component, that can be used to compose a GameObject.
 */
public interface Component {
    /**
     * Sets the GameObject that this component is attached to.
     *
     * @param gameObject the GameObject
     */
    void setGameObject(GameObject gameObject);

    /**
     * Gets the GameObject that this Component is attached to.
     *
     * @return the GameObject
     */
    GameObject getGameObject();

    /**
     * Initializes the Component.
     */
    void initialize();
}
