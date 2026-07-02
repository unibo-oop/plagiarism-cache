package util.image;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import javafx.scene.image.Image;

/**
 * a factory that generates sprites form a Sprite file path relative to the
 * directory containing the sprites.
 */
public class ImageFactoryWithCache implements ImageFactory {

    private final LoadingCache<String, Image> cache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Image>() {
                public Image load(final String key) {
                    return createImage(key);
                }
            });

    /**{@inheritDoc}**/@Override
    public Image getImageFromRelativePath(final String path) {
        return cache.getUnchecked(path);
    }

    private Image createImage(final String path) {
        return new Image(ClassLoader.getSystemResource(path).toString());
    }

}
