package breakout.view.utils;

import java.io.InputStream;

/**
 * A class for view utilities.<br/>
 * Implements some usefull methods to be used in scenes.
 *
 */
public final class Utils {
    /**
     * The width of the stage.
     */
    public static final double STAGE_WIDTH = 1200;
    /**
     * The height of the stage.
     */
    public static final double STAGE_HEIGHT = 700;

    /**
     * Private constructor. This is a utlity class.
     */
    private Utils() {
    }

    /**
     * Finds the path of the resource with the given name. A resource is some
     * data (images, audio, text, etc) that can be accessed by class code in a
     * way that is independent of the location of the code.
     * 
     * The name of a resource is a '/'-separated path name that identifies the
     * resource.
     * 
     * @param resName
     *            The resource to find
     * @return the path to the resource.
     */
    public static String getPath(final String resName) {
        return Utils.class.getResource(resName).toExternalForm();
    }

    /**
     * 
     * @param resName
     *            the resource to find
     * @return A {@link java.io.InputStream} object or null if no resource with
     *         this name is found
     */
    public static InputStream getResStream(final String resName) {
        return Utils.class.getResourceAsStream(resName);
    }
}
