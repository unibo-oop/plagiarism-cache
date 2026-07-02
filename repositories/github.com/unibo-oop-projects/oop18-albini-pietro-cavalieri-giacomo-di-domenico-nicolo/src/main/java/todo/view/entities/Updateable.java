package todo.view.entities;

/**
 * This interface represents something that can be updated every frame.
 */
@FunctionalInterface
public interface Updateable {
    /**
     * This function is called every frame.
     *
     * @param deltaTime represents the time in seconds since the last frame
     */
    void update(float deltaTime);
}
