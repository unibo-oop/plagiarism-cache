package view.scene;

import javafx.scene.Parent;

/**
 * This interface provides methods to load a layout from an existing resource.
 */
public interface LayoutLoader {

    /**
     * Loads a layout from an existing resource.
     * @param resourcePath - path to the resource
     * @return the loaded resource
     */
    Parent load(String resourcePath);
}
