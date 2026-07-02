package migglione.view.impl.scenesimpl;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Abstract class to help the scenes with the graphics.
 * 
 * <p>
 * During development we have seen that all of the scenes
 * would have used this kind of method, so we centralized its
 * override in this class
 */
public abstract class AbstractGamePanel extends JPanel {

    private static final long serialVersionUID = 9879879877L;

    /**
     * Override of the method paintComponent in JComponent.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Image panelImg = getImage();
        g.drawImage(panelImg, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Abstract method to be implementend in the scenes.
     * 
     * @return the image set in the scene, so that
     *         paintComponent can use it to draw in the panel
     */
    protected abstract Image getImage();
}
