package it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api;

import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.Distance;

/**
 * Interface for generating positions for new platforms.
 * Calculates valid coordinates based on difficulty parameters and previous platform positions.
 */
public interface PlatformPositionGenerator {

    /**
     * Generates a new position for a platform.
     * 
     * @param width the width of the new platform.
     * @param height the height of the new platform.
     * @param previousPlatformPosition the position of the previous platform to ensure valid spacing.
     * 
     * @return a Vector2d representing the new coordinates.
     */
    Vector2d generatePosition(double width, double height, Vector2d previousPlatformPosition);

    /**
     * Sets the difficulty context, which influences position generation parameters (e.g., distance).
     * 
     * @param distance the current distance settings
     */
    void setDistance(Distance distance);

}
