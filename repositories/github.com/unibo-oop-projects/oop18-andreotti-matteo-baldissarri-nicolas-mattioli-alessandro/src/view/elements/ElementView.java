package view.elements;

import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;

/**
 * Interface for generic element of the view.
 */
public interface ElementView {

    /**
     * 
     * @return dimension of element.
     */
    Dimension2D getDimension();

    /**
     * 
     * @return All sprites .
     */
    List<Image> getSprites();

    /**
     * 
     * @return the actual sprite position in the list.
     */
    int getSpriteCounter();

    /**
     * Increment the sprite counter.
     */
    void incSpriteCounter();

}
