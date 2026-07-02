package model.marker;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * 
 * Interface that identifies a notification during the game run.
 *
 */
public interface Marker {

    /**
     * Updates the marker's coordinates of the given size of movement.
     * @param velocity the amount of size of the movement.
     */
    void update(double velocity);

    /**
     * Checks if the marker is out of the screen.
     * @return true if the marker is out of the screen, false otherwise.
     */
    boolean isOutOfScreen();

    /**
     * Gets the coordinates' X.
     * @return the coordinates' X.
     */
    double getX();

    /**
     * Gets the coordinates' Y.
     * @return the coordinates' Y.
     */
    double getY();

    /**
     * Gets the marker's dimension.
     * @return the marker's dimension.
     */
    Dimension2D getDimension();

    /**
     * Gets the marker's image.
     * @return the marker's image.
     */
    Image getImage();

    /**
     * Gets the marker's notice.
     * @return the marker's notice.
     */
    String getText();

}
