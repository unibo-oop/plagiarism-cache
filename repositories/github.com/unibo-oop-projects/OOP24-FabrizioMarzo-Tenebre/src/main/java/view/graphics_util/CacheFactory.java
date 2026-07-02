package view.graphics_util;

import java.awt.image.BufferedImage;

/**
 * Factory interface for creating cache instances.
 */
public interface CacheFactory {

    /**
     * Creates and returns a cache for storing images identified by a String key.
     *
     * @return a cache instance mapping String keys to BufferedImage values
     */
    Cache<String, BufferedImage> imageCache();

}
