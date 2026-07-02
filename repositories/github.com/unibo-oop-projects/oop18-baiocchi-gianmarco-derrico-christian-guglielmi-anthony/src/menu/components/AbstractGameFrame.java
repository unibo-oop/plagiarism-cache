/**
 * 
 */
package menu.components;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * This abstract class extends {@link JFrame}, and must be implemented to
 * standardize the game design. This class allows easy management of the common
 * basic settings of the game frame. For further information see {@link JFrame} super class.
 */
public abstract class AbstractGameFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final URL IMG_URL_ICON = ClassLoader.getSystemResource("Icon.png");
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double SCALE = 2;
    private final int screenWidth;
    private final int screenHeight;

    /**
     * Creates a new game frame with default settings.
     */
    public AbstractGameFrame() {
        super();
        this.screenWidth = (int) (SCREEN_SIZE.getWidth() / SCALE);
        this.screenHeight = (int) (SCREEN_SIZE.getHeight() / SCALE);
        super.setIconImage(new ImageIcon(IMG_URL_ICON).getImage());
        super.setSize(this.screenWidth, this.screenHeight);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationByPlatform(true);
    }

    /**
     * Creates a new game frame with default settings and layout manager.
     * 
     * @param manager the LayoutManager
     */
    public AbstractGameFrame(final LayoutManager manager) {
        this();
        super.setLayout(manager);
    }

    /**
     * Sets the location of the window at the center of main monitor.
     * 
     * @param frameWidth the width of frame
     * @param frameHeight the height of frame
     */
    protected void setFrameInCenter(final int frameWidth, final int frameHeight) {
        super.setLocation(this.screenWidth - (int) (frameWidth / SCALE),
                this.screenHeight - (int) (frameHeight / SCALE));
    }
}
