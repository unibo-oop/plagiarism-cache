package it.unibo.the100dayswar.commons.utilities.impl;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Utility class that implements an IconLoader for the view's images.
 */
public final class IconLoader {
    private static final Logger LOGGER = Logger.getLogger(IconLoader.class.getName());

    /**
     * Private constructor to hide the implicit public one.
     */
    private IconLoader() {
    }

    /**
     * Load the Icon of each button.
     * 
     * @param path the path of the image
     * 
     * @return the icon created
     */
    public static Icon loadIcon(final String path) {
        return Optional.ofNullable(ClassLoader.getSystemResource(path))
            .map(ImageIcon::new)
            .orElseGet(() -> {
                LOGGER.log(Level.WARNING, "The icon at path " + path + " wasn't loaded.");
                return new ImageIcon(); // Fallback icon
            });
    }
}
