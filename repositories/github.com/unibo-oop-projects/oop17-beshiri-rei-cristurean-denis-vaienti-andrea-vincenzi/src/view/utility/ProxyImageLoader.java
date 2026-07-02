package view.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.scene.image.Image;
import utility.ImageType;

/**
 * Class that allow to load a image from disk.
 *
 */
public final class ProxyImageLoader implements ImageLoader {

    private final Map<ImageType, Image> loadedMap;
    private final RealImageLoader realLoader;
    private static ProxyImageLoader proxy;

    private ProxyImageLoader() {
        loadedMap = new HashMap<>();
        realLoader = new RealImageLoader();
    }

    /**
     * Get Image.
     */
    @Override
    public Image getImage(final ImageType image) {
        if (loadedMap.containsKey(image)) {
            return loadedMap.get(image);
        } else {
            loadedMap.put(image, realLoader.getImage(image));
            return loadedMap.get(image);
        }
    }

    /**
     * Singleton for proxy image loader.
     * @return Only instance of proxy image loader.
     */
    public static ProxyImageLoader get() {
        if (Objects.isNull(proxy)) {
            proxy = new ProxyImageLoader();
        }
        return proxy;
    }

    private class RealImageLoader implements ImageLoader {
        private static final String PATH = "/images/gameImages/";

        /**
         * Return loaded image.
         */
        @Override
        public Image getImage(final ImageType image) {
            return new Image(getClass().getResourceAsStream(PATH + image.getName()));
        }
    }
}
