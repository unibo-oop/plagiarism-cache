package controller.file.utilities;

import enums.SceneImage;
import javafx.scene.image.Image;

/**
 * Class utility for load the Scene Images. The class uses the Singleton
 * Pattern.
 */
public final class SceneImageUtility {

    // Instance of the class.
    private static final SceneImageUtility SINGLETON = new SceneImageUtility();

    /**
     * Method that implements the Singleton Pattern. It returns only one instance of
     * the class, created only one time.
     * 
     * @return the instance of the class.
     */
    public static SceneImageUtility getInstance() {
        return SINGLETON;
    }

    /*
     * Empty constructor of the class that load the images.
     */
    private SceneImageUtility() {
    }

    /**
     * Method that return the requested scene image. Given a Image enumeration, it
     * open the specified file and return it back.
     * 
     * @param sceneImageEnum the enumeration of the requested scene image.
     * @return the requested scene image.
     */
    public Image getSceneImage(final SceneImage sceneImageEnum) {
        Image sceneImage = null;
        final String path = sceneImageEnum.getPath();
        // Try to load the image from file.
        try {
            sceneImage = new Image(getClass().getResourceAsStream(path));
        } catch (final Exception e) {
            System.out.println("SPRITE " + sceneImage + " NOT FOUND");
        }
        return sceneImage;
    }

}
