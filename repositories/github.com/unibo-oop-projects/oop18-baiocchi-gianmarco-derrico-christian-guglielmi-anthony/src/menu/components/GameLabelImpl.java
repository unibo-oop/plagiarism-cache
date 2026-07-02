package menu.components;

import java.net.URL;

/**
 * This abstract class extends {@link AbstractGameLabel} and should be implemented to standardize
 * the game design. In this specified case, it's a display area for a externally
 * loaded image ({@link GameImgIcon}). For further information see {@link JLabel} super class.
 */
public class GameLabelImpl extends AbstractGameLabel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a GameLabelImpl with no image and with an empty string for the
     * title.
     */
    public GameLabelImpl() {
        super();
    }

    /**
     * Creates a GameLabel with ImageIcon loaded from the specified URL and
     * resized using a specified real scalar.
     * 
     * @param imgURL the URL pointer to get image "resource".
     * @param scale  the real scalar value to resize the image
     */
    public GameLabelImpl(final URL imgURL, final double scale) {
        super(imgURL, scale);
    }

    /**
     * Creates a GameLabel with ImageIcon loaded from the specified URL, resized
     * using a specified real scalar and using a specified image-scaling algorithm.
     * 
     * @param imgURL the URL pointer to get image "resource".
     * @param scale  the real scalar value to resize the image
     * @param hints  flags to indicate the type of algorithm to use for image resampling.
     */
    public GameLabelImpl(final URL imgURL, final double scale, final int hints) {
        super(imgURL, scale, hints);
    }
}
