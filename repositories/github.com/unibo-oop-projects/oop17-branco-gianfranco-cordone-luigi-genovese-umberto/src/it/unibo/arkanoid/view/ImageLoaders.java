package it.unibo.arkanoid.view;

/**
 * Final class for Singleton ImageLoader.
 */
public final class ImageLoaders {

 private static ImageLoader imgLoader;
 
 private ImageLoaders() {
 }

    /**
     * Getter for the Singleton imageLoader, the lazy version.
     * 
     * @return
     *         The instance of the Singleton ImageCacheLoader.
     */
    public static ImageLoader getImageLoader() {
        if (ImageLoaders.imgLoader == null) {
            ImageLoaders.imgLoader = new ImageCacheLoader();
        }
        return ImageLoaders.imgLoader;
    }

}
