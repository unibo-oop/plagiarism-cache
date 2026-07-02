package menu.components;

import java.net.URL;

import javax.swing.JLabel;

/**
 * This abstract class extends {@link JLabel} and should be implemented to
 * standardize the game design. In this specified case, it's a display area for
 * a externally loaded image ({@link GameImgIcon}). For further information see {@link JLabel} super
 * class.
 */
public abstract class AbstractGameLabel extends JLabel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a GameLabel with no image and with an empty string for the title.
     */
    public AbstractGameLabel() {
        super();
    }

    /**
     * Creates a GameLabel with ImageIcon loaded from the specified URL and resized
     * using a specified real scalar.
     * 
     * @param imgURL the URL pointer to get image "resource".
     * @param scale  the real scalar value to resize the image
     */
    public AbstractGameLabel(final URL imgURL, final double scale) {
        this();
        super.setIcon(new GameImgIcon(imgURL, scale));
    }

    /**
     * Creates a GameLabel with ImageIcon loaded from the specified URL, resized
     * using a specified real scalar and using a specified image-scaling algorithm.
     * 
     * @param imgURL the URL pointer to get image "resource".
     * @param scale  the real scalar value to resize the image
     * @param hints  flags to indicate the type of algorithm to use for image resampling.
     */
    public AbstractGameLabel(final URL imgURL, final double scale, final int hints) {
        this();
        super.setIcon(new GameImgIcon(imgURL, scale, hints));
    }
}
