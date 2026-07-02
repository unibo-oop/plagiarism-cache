package model.entitiesutil;

import java.util.Objects;

import util.Pair;

/**
 * Helper class to manage position and dimension of {@link Entity}s.
 */
public class Body {
    private Pair<Integer, Integer> position;
    private final int width;
    private final int height;

    /**
     * Helper class to manage position and dimension of {@link Entity}s.
     * 
     * @param position as (x,y) point coordinates {@link Pair}
     * @param width of the {@link Entity}
     * @param height of the {@link Entity}
     */
    public Body(final Pair<Integer, Integer> position, final int width, final int height) {
        this.position = Objects.requireNonNull(position);
        this.width = width;
        this.height = height;
    }

    /**
     * Update the position.
     * 
     * @param position as (x,y) point coordinates {@link Pair}
     * @return the old position
     */
    public Pair<Integer, Integer> setPosition(final Pair<Integer, Integer> position) {
        final Pair<Integer, Integer> oldPosition = this.position;
        this.position = Objects.requireNonNull(position);
        return oldPosition;
    }

    /**
     * Return the current position.
     * 
     * @return the current position
     */
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * Return the height.
     * 
     * @return the height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Return the width.
     * 
     * @return the width.
     */
    public int getWidth() {
        return this.width;
    }

}
