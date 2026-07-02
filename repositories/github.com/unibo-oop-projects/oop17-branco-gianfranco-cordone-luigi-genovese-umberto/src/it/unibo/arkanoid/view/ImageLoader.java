package it.unibo.arkanoid.view;

import javafx.scene.image.Image;

/**
 * An interface of ImageLoader that store and get Image.
 */
public interface ImageLoader {

    /**
     * 
     * @param image
     *            The image of {@link ImageID}.
     * @return The Image.
     */
    Image getImage(ImageID image);

}
