package it.unibo.plantsfarm.view.utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Utility class responsible for loading sprite images from the classpath.
 * This class loads a  BufferedImage using a resource path and keeps it.
 * It throws a RuntimeException if the image cannot be found or loaded.
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
    value = "EI",
    justification = "SpriteLoader intentionally exposes the loaded BufferedImage as a shared graphical resource."
)
public final class SpriteLoader {

    /**
     * The loaded sprite image.
     */
    private final BufferedImage image;

    /**
     * Creates a new {@code SpriteLoader} and loads the image located at the given resource path.
     *
     * @param resourcePath the path of the image resource inside the classpath
     * @throws RuntimeException if the resource is not found or cannot be loaded
     */
    public SpriteLoader(final String resourcePath) {
        final var stream = SpriteLoader.class.getResourceAsStream(resourcePath);

        if (stream == null) {
            throw new IllegalArgumentException("Image not found: " + resourcePath);
        }

        try (stream) {
            final BufferedImage img = ImageIO.read(stream);

            if (img == null) {
                throw new IllegalArgumentException("Unsupported image format: " + resourcePath);
            }

            this.image = img;

        } catch (final IOException e) {
            throw new IllegalArgumentException("Loading error: " + resourcePath, e);
    }
}

    /**
     * Returns the loaded sprite image.
     *
     * @return the BufferedImage loaded from the resource path
     */
    public BufferedImage getImage() {
        return image;
    }
}
