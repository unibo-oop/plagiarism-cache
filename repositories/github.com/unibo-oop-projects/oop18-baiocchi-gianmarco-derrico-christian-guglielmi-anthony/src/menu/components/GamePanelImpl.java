package menu.components;

import java.awt.LayoutManager;
import java.net.URL;

/**
 * This class extends {@link AbstractGamePanel}, and must be implemented to
 * standardize the game design. This class allows easy management of the common
 * basic settings of the game panel. For further information see {@link JPanel} super class.
 *
 */
public class GamePanelImpl extends AbstractGamePanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new GamePanel with a double buffer, a flow layout and a default background color.
     */
    public GamePanelImpl() {
        super();
    }

    /**
     * Creates a new buffered GamePanel with the specified layout manager.
     * 
     * @param manager the LayoutManager to use
     */
    public GamePanelImpl(final LayoutManager manager) {
        super(manager);
    }

    /**
     * Creates a new buffered GamePanel with the specified background image loaded from the specified URL and resized
     * using a specified real scalar.
     * 
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     */
    public GamePanelImpl(final URL imgURL, final double scale) {
        super(imgURL, scale);
    }

    /**
     * Creates a new buffered GamePanel with the specified background image loaded from the specified URL, resized 
     * using a specified real scalar and using a specified image-scaling algorithm.
     * 
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     * @param hints  flags to indicate the type of algorithm to use for image resampling.
     */
    public GamePanelImpl(final URL imgURL, final double scale, final int hints) {
        super(imgURL, scale, hints);
    }

    /**
     * Creates a new buffered GamePanel with the specified layout manager and the specified background image resized using a specified real scalar.
     * 
     * @param manager the LayoutManager to use
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     */
    public GamePanelImpl(final LayoutManager manager, final URL imgURL, final double scale) {
        super(manager, imgURL, scale);
    }

    /**
     * Creates a new buffered GamePanel with the specified layout manager, the specified background image loaded from
     * the specified URL, resized using a specified real scalar and using a specified image-scaling algorithm.
     * 
     * @param manager the LayoutManager to use
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     * @param hints  flags to indicate the type of algorithm to use for image resampling.
     */
    public GamePanelImpl(final LayoutManager manager, final URL imgURL, final double scale, final int hints) {
        super(manager, imgURL, scale, hints);
    }
}
