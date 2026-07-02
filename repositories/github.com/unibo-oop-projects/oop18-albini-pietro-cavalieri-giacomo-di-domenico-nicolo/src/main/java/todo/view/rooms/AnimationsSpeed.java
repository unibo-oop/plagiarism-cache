package todo.view.rooms;

/**
 * This interface represents the current speed of the UI animations, and allows
 * to change it.
 */
public interface AnimationsSpeed {
    /**
     * Returns the base speed of an animation, in seconds. Consumers of the
     * interface should then scale all their animations according to it.
     *
     * @return the base speed of animations
     */
    float baseSpeed();

    /**
     * Increase the speed of animations, whenever possible.
     *
     * @throws IllegalStateException if the speed can't be increased anymore
     */
    void increase();

    /**
     * Decrease the speed of animations, whenever possible.
     *
     * @throws IllegalStateException if the speed can't be decreased anymore
     */
    void decrease();

    /**
     * @return true if the speed can be increased
     */
    boolean canIncrease();

    /**
     * @return true if the speed can be decreased
     */
    boolean canDecrease();
}
