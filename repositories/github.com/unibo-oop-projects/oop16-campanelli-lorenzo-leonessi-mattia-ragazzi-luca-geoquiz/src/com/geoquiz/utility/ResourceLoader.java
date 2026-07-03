package com.geoquiz.utility;

import java.io.InputStream;
import java.net.URL;

/**
 * Utility class with some static methods for resource loading from classpath.
 *
 */
public final class ResourceLoader {

    private ResourceLoader() {

    }

    /**
     * @param fileName
     *           the name of the file to be loaded
     * @return an URL indicating the path of the specified file
     */
    public static URL loadResource(final String fileName) {
        return ResourceLoader.class.getResource(fileName);
    }

    /**
     * @param fileName
     *            the name of the file to be loaded
     * @return an InputStream to be used to read the file
     */
    public static InputStream loadResourceAsStream(final String fileName) {
        return ResourceLoader.class.getResourceAsStream(fileName);
    }
}
