package ballblast.model.gameobjects;

import java.util.Optional;

/**
 * All different types of {@link Ball}.
 */
public enum BallType {
    /**
     * small.
     */
    SMALL(8),
    /**
     * medium.
     */
    MEDIUM(12),
    /**
     * large.
     */
    LARGE(16);

    private BallType child;
    private double diameter;

    static {
        SMALL.child = null;
        MEDIUM.child = SMALL;
        LARGE.child = MEDIUM;
    }

    /**
     * Create a {@link BallType} instance.
     * 
     * @param diameter the diameter of the {@link Ball}.
     */
    BallType(final double diameter) {
        this.diameter = diameter;
    }

    /**
     * Gets the {@link Optional} represents the {@link BallType} to split into.
     * 
     * @return an empty {@link Optional} if the {@link Ball} is not divisible, an
     *         {@link BallType} otherwise.
     */
    public Optional<BallType> getChild() {
        return Optional.ofNullable(child);
    }

    /**
     * Gets the {@link Ball}'s diameter.
     * 
     * @return the diameter of the {@link Ball}.
     */
    public double getDiameter() {
        return this.diameter;
    }
}
