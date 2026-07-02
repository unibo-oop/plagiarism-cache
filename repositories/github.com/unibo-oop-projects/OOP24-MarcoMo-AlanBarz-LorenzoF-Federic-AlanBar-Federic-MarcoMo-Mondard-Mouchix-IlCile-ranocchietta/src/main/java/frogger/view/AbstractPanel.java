package frogger.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import frogger.common.Constants;
import frogger.controller.AbstractController;

/**
 * Abstract base class for all panels in the Frogger game.
 * Provides common functionality for background image handling and controller management.
 *
 * @param <X> the type of the controller associated with this panel.
 */
public abstract class AbstractPanel<X extends AbstractController> extends JPanel {
    /*added because the class JPanel implements Serializable. */
    private static final long serialVersionUID = 1L;
    /*The controller associated with this panel.*/
    private X controller;
    /*The background image for this panel.*/
    private BufferedImage background;

    /**
     * Sets up input listeners.
     * This method must be implemented by subclasses to handle user input.
     */
    protected abstract void setInputListener();

    /**
     * Imports all necessary images for this panel.
     * This method must be implemented by subclasses to load resources.
     */
    protected abstract void importImg();

    /**
     * Sets the controller for this panel and initializes the panel.
     * @param controller the controller to associate with this panel
     */
    public void setController(final X controller) {
        this.controller = controller;
        this.importImg();
        this.setInputListener();
    }

    /**
     * Sets the preferred size of the panel based on predefined constants.
     */
    protected void setPanelSize() {
        setPreferredSize(new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT));
    }

    /**
     * Returns the controller associated with this panel.
     * @return the controller
     * @inheritDoc
     */
    protected X getController() {
        return controller;
    }

    /**
     * Paints the background image onto the panel.
     * @param g the Graphics context to use for painting
     */
    protected void paintBackground(final Graphics g) {
        g.drawImage(background, 0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, null);
    }

    /**
     * Returns the background image of the panel.
     * @return the background image
     */
    protected BufferedImage getBackgroundImage() {
        return background;
    }

    /**
     * Sets the background image for the panel.
     * @param background the BufferedImage to set as background
     */
    protected void setBackgroundImage(final BufferedImage background) {
        this.background = background;
    }
}
