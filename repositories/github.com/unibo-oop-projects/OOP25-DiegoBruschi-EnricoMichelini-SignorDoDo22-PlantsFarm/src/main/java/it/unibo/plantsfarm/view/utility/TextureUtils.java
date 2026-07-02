package it.unibo.plantsfarm.view.utility;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import javax.swing.ImageIcon;

/**
 * Utility class for managing icons.
 */
public final class TextureUtils {

    private static final float DARKEN_FACTOR = 0.3f;
    private static final float BRIGHT_FACTOR = 0.0f;
    private static final int ZERO = 0;

    private TextureUtils() {
        // Private constructor
    }

    /**
     * Creates a locked version of an icon.
     * 
     * @param coloredIcon The original icon.
     * @return A darkened version of the icon.
     */
    public static ImageIcon createLockedIcon(final ImageIcon coloredIcon) {
        if (coloredIcon == null) {
            return null;
        }

        final Image icon = coloredIcon.getImage();
        final int width = icon.getWidth(null);
        final int height = icon.getHeight(null);

        if (width <= ZERO || height <= ZERO) {
            return coloredIcon;
        }

        final BufferedImage lockedIcon = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        lockedIcon.getGraphics().drawImage(icon, ZERO, ZERO, null);

        final RescaleOp op = new RescaleOp(DARKEN_FACTOR, BRIGHT_FACTOR, null);
        return new ImageIcon(op.filter(lockedIcon, null));
    }
}
