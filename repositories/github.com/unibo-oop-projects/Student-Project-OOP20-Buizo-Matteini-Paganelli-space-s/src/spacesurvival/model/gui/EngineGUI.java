package spacesurvival.model.gui;

import spacesurvival.utilities.LinkActionGUI;

import java.awt.Rectangle;
import java.util.List;

/**
 * Implements the methods that all models GUI must have.
 */
public interface EngineGUI {

    /**
     * Return the main action for this GUI.
     * 
     * @return the main action of type ActionGUI.
     */
    LinkActionGUI getMainLink();

    /**
     * Return the rectangle represent the GUI.
     * 
     * @return a Rectangle
     */
    Rectangle getRectangle();

    /**
     * Return the state of the visibility of the GUI.
     * 
     * @return a Visibility enumeration.
     */
    Visibility getVisibility();

    /**
     * Return a list of possible links of the GUI.
     * 
     * @return a list of ActionGUI
     */
    List<LinkActionGUI> getLinks();

    /**
     * Set the visibility of the GUI.
     * 
     * @param state the state to set
     */
    void setVisibility(Visibility state);

    /**
     * Return a boolean representing the visibility of the GUI.
     * 
     * @return true is the GUI is visible
     */
    boolean isVisible();

}
