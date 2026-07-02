package spacesurvival.view.dead;

import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.utilities.GraphicsText;

import java.awt.Color;
import java.util.List;

/**
 * Interface that implements the dead functionalities.
 */
public interface GUIDead extends GUI, GraphicsText {

    /**
     * Set text all text button.
     * 
     * @param listText a list of text.
     */
    void setTextButtons(List<String> listText);

    /**
     * Set linkAction a back buttonLink.
     * 
     * @param mainAction is linkAction a current GUI.
     * @param linkAction for link previous GUI.
     */
    void setBtnActions(LinkActionGUI mainAction, List<LinkActionGUI> linkAction);

    /**
     * Set foreground color of title.
     * @param color
     */
    void setForegroundTitle(Color color);
}
