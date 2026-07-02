package it.unibo.view.interfaces;

import it.unibo.model.Point2DI;

/**
 * Interface representing the view model for the cannon in the game.
 * Provides methods to determine the image for a specific angle and to get the cannon's center position.
 */
public interface CannonViewInterface {

    /**
     * Gets the image index corresponding to the cannon's current angle.
     * This is used to determine which image or sprite should be displayed based on the cannon's orientation.
     * 
     * @param angle The angle of the cannon.
     * @return The index of the image corresponding to the specified angle.
     */
    int getImageIndexForAngle(final double angle);

    /**
     * Gets the current center position of the cannon in the view.
     * This is typically used to render the cannon at the correct location on the screen.
     * 
     * @return The center position of the cannon as a Point2DI object.
     */
    Point2DI getCenter();
}
