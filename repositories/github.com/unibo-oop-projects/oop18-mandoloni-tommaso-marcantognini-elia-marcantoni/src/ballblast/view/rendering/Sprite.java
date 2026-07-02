package ballblast.view.rendering;

import java.io.FileNotFoundException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;
import ballblast.view.imageloader.ImagePath;
import javafx.scene.image.Image;

/**
 * Represents an image that can be rendered on the screen at a certain position
 * on the screen and with some rotation. The image source is specified as an
 * {@link ImagePath}.
 * 
 */
public interface Sprite extends Renderer {
    /**
     * Sets the position of the sprite in pixel coordinates relative to the pivot.
     * 
     * @param coordinate the position to be set.
     */
    void setPosition(Coordinate coordinate);

    /**
     * Returns the position of the sprite in pixel coordinates relative to the
     * pivot.
     * 
     * @return the position.
     */
    Coordinate getPosition();

    /**
     * 
     * @return the {@link Image} source.
     */
    Image getImageSource();

    /**
     * 
     * @param topLeft The top left corner position. Default is (0, 0)
     * @param offset  The offset of the bottom right corner from the top left one.
     */
    void setSourceWindow(Coordinate topLeft, Vector2D offset);

    /**
     * Sets the source image of the sprite.
     * 
     * @param source the new source image identifier.
     * @throws FileNotFoundException the file not found.
     */
    void setSource(ImagePath source) throws FileNotFoundException;

    /**
     * Sets the opacity of the sprite. Values [0,1].
     * 
     * @param alpha the new alpha value.
     */
    void setAlpha(double alpha);

    /**
     * Returns the opacity of the sprite.
     * 
     * @return the alpha value.
     */
    double getAlpha();

    /**
     * 
     * @return return the image source height.
     */
    double getImageSourceHeight();

    /**
     * 
     * @return return the image source width.
     */
    double getImageSourceWidth();

    /**
     * Sets the width on the screen of the sprite, according to the
     * GameObject width.
     * 
     * @param width the new width.
     */
    void setGameObjectWidth(double width);

    /**
     * 
     * Sets the width on the screen of the sprite, according to the
     * GameObject height.
     * 
     * @param height the new height.
     */
    void setGameObjectHeight(double height);

    /**
     * Sets the position on the screen of the sprite, according to the
     * GameObject position.
     * 
     * @param position the new position
     */
    void setGameObjectPosition(Coordinate position);

    /**
     * 
     * @return the position of the GameObject.
     */
    Coordinate getGameObjectPosition();

    /**
     * 
     * @return the width of the GameObject.
     */
    double getGameObjectWidth();

    /**
     * 
     * @return the height of the GameObject.
     */
    double getGameObjectHeight();

    /**
     * Returns the position of the top-left corner of the source rectangle.
     * 
     * @return the top-left corner.
     */
    Coordinate getSourceTopLeftCorner();

}
