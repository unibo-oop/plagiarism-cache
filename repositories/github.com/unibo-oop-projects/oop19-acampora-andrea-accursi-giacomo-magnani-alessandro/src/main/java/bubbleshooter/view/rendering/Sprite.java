package bubbleshooter.view.rendering;

import java.io.FileNotFoundException;
import javafx.geometry.Point2D;
import bubbleshooter.view.images.ImagePath;

/**
 * Represents an image that can be drawn on the screen at a certain position on
 * the screen.
 * 
 */
public interface Sprite {

    /**
     * Draws the {@link Sprite}.
     */
    void draw();

    /**
     * Sets the position.
     * 
     * @param coordinate The position of the sprite.
     */
    void setPosition(Point2D coordinate);

    /**
     * Sets the image.
     * 
     * @param source The path of the image.
     * @throws FileNotFoundException The loading of the image may fail.
     */
    void setSource(ImagePath source) throws FileNotFoundException;

    /**
     * Sets the height.
     * 
     * @param height The height of the sprite.
     */
    void setHeight(double height);

    /**
     * Sets the width.
     * 
     * @param width The width of the sprite.
     */
    void setWidth(double width);

    /**
     * Gets the position.
     * 
     * @return the potition
     */
    Point2D getPosition();

    /**
     * Gets the width.
     * 
     * @return width.
     */
    double getWidth();

    /**
     * Gets the height.
     * 
     * @return height.
     */
    double getHeight();

}
