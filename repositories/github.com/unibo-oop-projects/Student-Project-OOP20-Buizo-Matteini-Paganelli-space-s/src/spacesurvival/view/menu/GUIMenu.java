package spacesurvival.view.menu;

import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.utilities.GraphicsText;

import java.util.List;

/**
 * Interface that implements the menu functionalities.
 */
public interface GUIMenu extends GUI, GraphicsText {

    /**
     * Sets the texts of the menu GUI buttons.
     * @param listNames is list text buttons.
     */
    void setTextButtons(List<String> listNames);

    /**
     * Sets menu links to other GUIs.
     * @param mainAction is connection of the current GUI.
     * @param linksAction is connection of the current GUI.
     */
    void setBtnActions(LinkActionGUI mainAction, List<LinkActionGUI> linksAction);

    /**
     * Set the space of the JTextField.
     * @param sizeColumn for space JTextField.
     */
    void setColumnsNamePlayer(int sizeColumn);

    /**
     * Get text of name' player.
     * @return text of name' player.
     */
    String getTextNamePalyer();
}
