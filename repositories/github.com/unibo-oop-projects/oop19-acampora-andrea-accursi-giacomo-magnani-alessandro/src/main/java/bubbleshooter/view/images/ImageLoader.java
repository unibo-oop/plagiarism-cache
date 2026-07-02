package bubbleshooter.view.images;

import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * Image loader.
 */
public final class ImageLoader {

    /**
     * map used as a cache for already created images.
     */
    private static final Map<ImagePath, Image> IMAGES = new HashMap<>();

    private ImageLoader() {
    }

    /**
     * @param imagePath the path of the image to get.
     * @return the image that refers to the specified {@link ImagePath}
     */
    public static Image getImage(final ImagePath imagePath) {
        return IMAGES.get(imagePath);
    }

    /**
     * Loads all images.
     */
    public static void loadAll() {
        Arrays.stream(ImagePath.values()).forEach(p -> IMAGES.put(p, loadImage(p.getPath())));

    }

    /**
     * 
     * @param the imagePath to take the image.
     * @return the created image.
     */
    private static Image loadImage(final String imagePath) {
        return new Image(ImageLoader.class.getResourceAsStream(imagePath));
    }
}
