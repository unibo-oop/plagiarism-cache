package spacesurvival.view;

import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.utilities.ButtonLink;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Interface that implements the contract that each GUI must have.
 */
public interface GUI {

    /**
     * Adds a MouseListener to the GUI.
     * @param mouseListener added to the GUI.
     */
    void addMouseListener(MouseListener mouseListener);

    /**
     * Get list link button for other GUIs. 
     * @return List of ButtonLink
     */
    List<ButtonLink> getBtnActionLinks();

    /**
     * Sets the link identifier of the GUI.
     * @param linkActionGUI link identifier.
     */
    void setMainAction(LinkActionGUI linkActionGUI);

    /**
     * Set the boundaries from a rectangle.
     * @param rectangle for borders.
     */
    void setBounds(Rectangle rectangle);

    /**
     * Set visibility of the GUI.
     * @param visible for visibility.
     */
    void setVisible(boolean visible);

    /**
     * Set background image.
     * @param path of image.
     */
    void setImageBackground(String path);

    /**
     * Set the border color and thickness.
     * @param color for border.
     * @param thickness for border.
     */
    void setBorder(Color color, int thickness);

    /**
     * Set visibility of the foreground panel of the GUI.
     * @param visible for visibility panel.
     */
    void setVisibleGlassPanel(Visibility visible);

    /**
     * Close the GUI and destroyed JFrame.
     */
    void close();

}
