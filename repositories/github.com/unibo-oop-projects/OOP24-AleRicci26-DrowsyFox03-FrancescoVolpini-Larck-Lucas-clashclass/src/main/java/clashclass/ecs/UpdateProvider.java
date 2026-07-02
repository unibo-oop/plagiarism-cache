package clashclass.ecs;

/**
 * Represents an object which internal state should be updated once per frame.
 */
@FunctionalInterface
public interface UpdateProvider {

    /**
     * Updates the Component once per frame.
     *
     * @param deltaTime time elapsed between the previous and the current frame.
     */
    void update(float deltaTime);
}
