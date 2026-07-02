package it.unibo.jtrs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * An utility class to load custom resources.
 */
public final class ResourceLoader {

    private ResourceLoader() { }

    /**
     * Returns an InputStream to a given file.
     *
     * @param fileName the file name
     * @return the InputStream of the file
     * @throws IOException if that resource can not be found
     */
    public static InputStream loadAsStream(final String fileName) throws IOException {
        final var resource = ResourceLoader.class.getResourceAsStream("/" + fileName);
        if (resource == null) {
            throw new IOException("Can not load " + fileName);
        }
        return resource;
    }

    /**
     * Returns the url of the given file.
     * 
     * @param fileName the file name
     * @return the URL of the file
     * @throws IOException if that resource can not be found
     */
    public static URL loadAsUrl(final String fileName) throws IOException {
        final var resource = ResourceLoader.class.getResource("/" + fileName);
        if (resource == null) {
            throw new IOException("Can not load " + fileName);
        }
        return resource;
    }

}
