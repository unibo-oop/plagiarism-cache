package spacesurvival.view;

import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.utilities.FactoryGUIs;
import spacesurvival.view.utilities.JPanelImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Implements the basis for each GUI.
 */
public abstract class AbstractGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JPanelImage panelBackground;

    private final JPanel panelGlass;

    private LinkActionGUI mainAction;

    /**
     * Constructor for basis GUI.
     */
    public AbstractGUI() {
        super();
        this.panelBackground = new JPanelImage();
        this.panelGlass = FactoryGUIs.createPanelTransparent(new BorderLayout());
        super.setContentPane(this.panelBackground);
        super.setGlassPane(this.panelGlass);
        FactoryGUIs.setDefaultJFrame(this);
    }

    /**
     * Sets the link identifier of the GUI.
     * @param linkActionGUI link identifier.
     */
    public void setMainAction(final LinkActionGUI linkActionGUI) {
        this.mainAction = linkActionGUI;
    }

    /**
     * Set the boundaries from a rectangle.
     * @param rectangle for borders.
     */
    public void setBounds(final Rectangle rectangle) {
        super.setBounds(rectangle);
        this.panelBackground.setBounds(rectangle);
        this.panelGlass.setBounds(rectangle);
    }

    /**
     * Set background image.
     * @param path of image.
     */
    public void setImageBackground(final String path) {
        this.panelBackground.setImage(path, super.getSize());
    }

    /**
     * Set the border color and thickness.
     * @param color for border.
     * @param thickness for border.
     */
    public void setBorder(final Color color, final int thickness) {
        final Border lobstered = BorderFactory.createLoweredBevelBorder();
        final Border line = BorderFactory.createLineBorder(color, thickness);

        this.panelBackground.setBorder(BorderFactory.createCompoundBorder(lobstered, line));
    }

    /**
     * Add items to the foreground panel from an index.
     * @param component added.
     * @param index for added.
     */
    public void addFrontPanel(final Component component, final String index) {
        this.panelGlass.add(component, index);
    }

    /**
     * Set visibility of the foreground panel of the GUI.
     * @param visible for visibility panel.
     */
    public void setVisibleGlassPanel(final Visibility visible) {
        this.panelGlass.setVisible(visible.isVisible());
    }

    /**
     * Close the GUI and destroyed JFrame.
     */
    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
