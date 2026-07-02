package view.utility;

import javafx.scene.image.Image;
import utility.ImageType;

/**
 * Interface that represent image loader.
 *
 */
public interface ImageLoader {

    /**
     * Get loaded image.
     * 
     * @param image
     *            Image to load.
     * @return Image.
     */
    Image getImage(ImageType image);
}
