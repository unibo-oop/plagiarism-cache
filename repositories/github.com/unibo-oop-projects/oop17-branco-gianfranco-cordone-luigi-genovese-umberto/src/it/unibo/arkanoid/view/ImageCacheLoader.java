package it.unibo.arkanoid.view;

import java.util.EnumMap;
import java.util.Map;
import javafx.scene.image.Image;


/**
 * Class used to store and read image. It is designed using Singleton pattern,
 * the lazy version.
 * 
 */
public final class ImageCacheLoader implements ImageLoader {

    private final Map<ImageID, Image> imageStore;

    /**
     * Constructors of ImageCacheLoader.
     */
    public ImageCacheLoader() {
        this.imageStore = new EnumMap<>(ImageID.class);
    }


    /**
     * Return the image if is present in the map or put in otherwise.
     * 
     * @param image
     *            The image to get.
     * 
     * @return Image present in the store.
     */
    @Override
    public Image getImage(final ImageID image) {
        try {
            if (!this.imageStore.containsKey(image)) {
                this.imageStore.put(image, new Image(ImageCacheLoader.class.getResourceAsStream(image.getPath())));
            }
            return this.imageStore.get(image);
        } catch (final Exception e) {
            System.out.println("Error, can't load: " + image.getPath());
        }
        return null;
    }
}
