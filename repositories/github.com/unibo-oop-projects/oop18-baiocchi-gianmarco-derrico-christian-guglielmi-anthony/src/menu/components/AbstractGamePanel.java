package menu.components;

import java.awt.Color;
import java.awt.LayoutManager;
import java.net.URL;

import javax.swing.JPanel;

/**
 * This abstract class extends {@link JPanel}, and must be implemented to
 * standardize the game design. This class allows easy management of the common
 * basic settings of the game panel. For further information see {@link JPanel} super class.
 *
 */
public abstract class AbstractGamePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
//    private static final Color BACKGROUND = new Color(81, 81, 81);
    private static final Color BACKGROUND_COLOR = Color.BLACK;

    /**
     * Creates a new GamePanel with a double buffer, a flow layout and a default background color.
     */
    public AbstractGamePanel() {
        super();
        super.setBackground(BACKGROUND_COLOR);
    }

    /**
     * Creates a new buffered GamePanel with the specified layout manager.
     * 
     * @param manager the LayoutManager to use
     */
    public AbstractGamePanel(final LayoutManager manager) {
        this();
        super.setLayout(manager);
    }

    /**
     * Creates a new buffered GamePanel with the specified background image loaded from the specified URL and resized
     * using a specified real scalar.
     * 
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     */
    public AbstractGamePanel(final URL imgURL, final double scale) {
        this();
        super.add(new GameLabelImpl(imgURL, scale));
    }

    /**
     * Creates a new buffered GamePanel with the specified background image loaded from the specified URL, resized 
     * using a specified real scalar and using a specified image-scaling algorithm.
     * 
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     * @param hints  flags to indicate the type of algorithm to use for image resampling.
     */
    public AbstractGamePanel(final URL imgURL, final double scale, final int hints) {
        this();
        super.add(new GameLabelImpl(imgURL, scale, hints));
    }

    /**
     * Creates a new buffered GamePanel with the specified layout manager and the specified background image loaded
     * from the specified URL and resized using a specified real scalar.
     * 
     * @param manager the LayoutManager to use
     * @param imgURL the URL pointer to get image "resource"
     * @param scale the real scalar value to resize the image
     */
    public AbstractGamePanel(final LayoutManager manager, final URL imgURL, final double scale) {
        this(imgURL, scale);
        super.setLayout(manager);
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
    public AbstractGamePanel(final LayoutManager manager, final URL imgURL, final double scale, final int hints) {
        this(imgURL, scale, hints);
        super.setLayout(manager);
    }
}
