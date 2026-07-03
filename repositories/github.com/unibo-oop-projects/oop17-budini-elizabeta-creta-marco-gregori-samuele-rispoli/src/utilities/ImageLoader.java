package utilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;



/**
 * This class is used to obtain a new ImageIcon from the filename. Since we only
 * need one object of this class, this class was designed using the Singleton
 * pattern to avoid other instances.
 */
public final class ImageLoader {
    private final Map<String, ImageIcon> loadedImages = new HashMap<>();

    /**
     * Since the class was designed using Singleton pattern, the constructor is
     * private.
     */
    private ImageLoader() {
    }

    /**
     * In order to guarantee fully thread-safeness, the SINGLETON instance's
     * definition is nested inside the main class ImageLoader. The Singleton will be
     * created upon the first call to getInstance().
     */
    private static class LoaderSingleton {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }

    /**
     * Upon the first call, this method will create a new Singleton instance.
     * 
     * @return The Singleton instance of the ImageLoader.
     */
    public static ImageLoader getInstance() {
        return LoaderSingleton.INSTANCE;
    }

    /**
     * This method is used to return an ImageIcon out of the file represented by the
     * path.
     * 
     * @param path
     *            The image's path inside the project folder.
     * @return An imageicon of the input image.
     */
    public ImageIcon getImage(final String path) {
        if (!this.loadedImages.containsKey(path)) {
            final URL imgURL = ImageLoader.class.getResource("/" + path);
            this.loadedImages.put(path, new ImageIcon(imgURL));
        }
        return this.loadedImages.get(path);
    }

}
