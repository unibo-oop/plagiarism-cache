package model.entitiesutil;

/**
 * Possible states a {@link Bubble} could find itself in.
 */
public enum BubbleState {
    /**
     * The {@link Bubble} has just been shot and moves horizontally.
     */
    MOVING_HORIZONTALLY,
    /**
     * The {@link Bubble} begins its ascent after traveling a bit horizontally.
     */
    MOVING_VERTICALLY,
    /**
     * The {@link Bubble} has reached the ceiling and now floats up and down while it waits to explode.
     */
    FLOATING;
}
