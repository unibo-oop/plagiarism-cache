package util.image;

import javafx.scene.image.Image;

/**
 * generates objects for the view.
 */
public interface ImageFactory {

    /**
     * 
     * @param path the path relative to the sprite folder
     * @return the object associated with the id
     */
    Image getImageFromRelativePath(String path);

}
