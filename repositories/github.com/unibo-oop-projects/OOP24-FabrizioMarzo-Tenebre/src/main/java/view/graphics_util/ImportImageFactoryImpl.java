package view.graphics_util;

import java.io.InputStream;
import java.util.Optional;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Implementation of ImportImageFactory that provides image loaders
 * for PNG, JPNG, and JPEG formats with caching support.
 */
public class ImportImageFactoryImpl implements ImportImageFactory {

    /**
     * Creates an image loader for the given file extension.
     * The loader caches loaded images to improve performance.
     *
     * @param extension the file extension of the image format (e.g., ".png")
     * @return an ImportImage instance that loads images with the specified
     *         extension
     */
    private ImportImage createLoader(final String extension) {
        return new ImportImage() {

            private final CacheFactory cacheFactory = new CacheFactoryImpl();
            private Cache<String, BufferedImage> cache = cacheFactory.imageCache();

            @Override
            public Optional<BufferedImage> imp(final String path) {
                if (cache.contains(path)) {
                    return Optional.of(cache.get(path));
                }
                try (InputStream is = getClass().getResourceAsStream(path + extension)) {
                    if (is == null) {
                        System.err.println("Image not found: " + path + extension);
                        return null;
                    }
                    BufferedImage img = ImageIO.read(is);
                    cache.put(path, img);
                    return Optional.of(img);
                } catch (Exception e) {
                    System.err.println("Error loading image: " + path + extension);
                    e.printStackTrace();
                    return Optional.empty();
                }
            }
        };
    }

    @Override
    public ImportImage loaderPNG() {
        return this.createLoader(".png");
    }

    @Override
    public ImportImage loaderJPNG() {
        return this.createLoader(".jpng");
    }

    @Override
    public ImportImage loaderJPEG() {
        return this.createLoader(".jpeg");
    }

}
