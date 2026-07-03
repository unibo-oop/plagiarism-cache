package util;

import javafx.scene.image.Image;
import util.ImageLoaderProxy.ImageID;

/**
 * Provides a method to load images from their own identifier using the proxy
 * design pattern. The real image loading algorith is meant to be decoupled from
 * the user class as well as the image managing policy.
 */
public interface ImageLoader {

    /**
     * Getter of an Image object.
     * 
     * @param identifier
     *            the Image identifier used in the enum {@link ImageID} to identify
     *            a specific image
     * @return the Image object relative to it's identifier contained in the ImageID
     *         enum
     */
    Image getImage(ImageID identifier);
}
