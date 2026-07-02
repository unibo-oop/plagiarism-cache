package vg.utils;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Utility class for ImageFX.
 */
public final class ImageFXUtils {
    private ImageFXUtils() {
    }

    /**
     * Provides a new ImagePattern from the given image.
     * @param pathImage the path of the image
     * @return the new ImagePattern
     */
    public static ImagePattern createImagePatternFrom(final String pathImage) {
        final Image image = new Image(pathImage);
        return new ImagePattern(image);
    }
}
