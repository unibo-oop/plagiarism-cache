package view;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * A class that basically translates the type of entity to draw to the corresponding Image.
 * It contains a map <TranslationToFile, Image> that keeps already loaded images for faster access (like a cache).
 * EntityVisualLoader uses the Singleton pattern for initialisation.
 */
public final class EntityVisualLoader {

    private static EntityVisualLoader visualLoader;
    private final Map<String, Image> cache;

    private EntityVisualLoader() {
        cache = new HashMap<>();
    }

    /**
     * Method to get the only instance of the EntityVisualLoader Singleton.
     * @return SINGLETON 
     */
    public static EntityVisualLoader getVisualLoader() {
        if (visualLoader == null) {
            visualLoader = new EntityVisualLoader();
        }
        return visualLoader;
    }

    /**
     * Method that simply gets the needed resources from files indicated by the string passed as an argument
     * and saves the resulting images to a cache. Obviously, if there are any problems
     * locating the files, it throws a FileNotFoundException.
     * @param s the String of the entity's texture to get
     * @return the selected Image
     * @throws FileNotFoundException 
     */
    public Image getResource(final String s) throws FileNotFoundException {
        /*
         *      If Image was cached already, the method takes 
         *      it from the internal map, so it's faster
         *      and doesn't need access to the filesystem.
         */ 
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        /*      Otherwise: Image is loaded from file.        */
        BufferedInputStream str = new BufferedInputStream(new FileInputStream(s));
        final Image img = new Image(str);
        cache.put(s, img);
        return img;
    }

}
