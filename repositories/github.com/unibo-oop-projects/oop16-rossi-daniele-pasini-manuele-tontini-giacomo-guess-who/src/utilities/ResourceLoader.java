package utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;

/**
 * Allow to load resources from a given path (even if the application is run
 * throw a jar file).
 * 
 */
public final class ResourceLoader {

    private ResourceLoader() {

    }

    /**
     * Returns an inputStream relative to the file specified in the path
     * parameter.
     * 
     * @param path the path relative to the resource needed
     * @return an inputStream relative to the file specified in the path
     */
    public static InputStream load(final String path) {
        return ResourceLoader.class.getResourceAsStream(path);
    }

    /**
     * Returns an URL relative to the file specified in the path parameter.
     * 
     * @param path the path relative to the resource needed
     * @return an URL relative to the file specified in the path
     */
    public static URL getURL(final String path) {
            return ResourceLoader.class.getResource(path);
    }

    /**
     * Returns a BufferedImage relative to the file specified in the path parameter.
     * 
     * @param path path the path relative to the resource needed
     * @return a BufferedImage relative to the file specified in the path
     */
    public static BufferedImage loadImage(final String path) {
        try {
            return ImageIO.read(ResourceLoader.load(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns the content of a text file specified in the path parameter.
     * @param path the path of the file that should be listed
     * @return the content of the file read
     */
    public static String loadFile(final String path) {
        final StringBuilder file = new StringBuilder();
        final String lineFeed = System.getProperty("line.separator");
        try (InputStream in = ResourceLoader.load(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            br.lines().forEach(x-> file.append(x + lineFeed));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString();
    }

    /**
     * List all files within path.
     * 
     * @param path the path that should be scanned
     * @return the list of resources
     */
    public static List<String> listResourceFiles(final String path) {
        final List<String> filenames = new ArrayList<>();
        try {
            final File jarFile = new File(ResourceLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            if (jarFile.isFile()) { // Application executed throw runnable jar
                final JarFile jar = new JarFile(jarFile);
                final Enumeration<JarEntry> entries = jar.entries();
                final String normalizedPath = normalizePath(path);
                while (entries.hasMoreElements()) {
                    final String entryName = entries.nextElement().getName();
                    if (entryName.startsWith(normalizedPath)) {
                        final String fileName = entryName.substring(normalizedPath.length() + 1, entryName.length());
                        if (!fileName.isEmpty()) {
                            filenames.add(fileName);
                        }
                    }
                }
                jar.close();
            } else { // // Application executed throw IDE
                filenames.addAll(Arrays.asList(ResourceLoader.loadFile(path).split(System.getProperty("line.separator"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filenames;
    }

    /**
     * return a path without the starting filename separator / \
     */
    private static String normalizePath(final String path) {
        String newPath = "";
        if (path.startsWith("/") || path.startsWith("\\")) {
            newPath = path.substring(1);
        }
        return newPath;
    }

}
