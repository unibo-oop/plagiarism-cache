package spacesurvival.view.help;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.utilities.GraphicsText;

import java.util.List;

/**
 * Interface that implements the help functionalities.
 */
public interface GUIHelp extends GUI, GraphicsText {

    /**
     * Set linkAction a back buttonLink.
     * 
     * @param mainAction is linkAction a current GUI.
     * @param linkAction for link previous GUI.
     */
    void setActionBtnBack(LinkActionGUI mainAction, LinkActionGUI linkAction);

    /**
     * Set text a all units help.
     * 
     * @param listText a list of text.
     */
    void setTextUnit(List<String> listText);

    /**
     * Set text all text button.
     * 
     * @param listText a list of text.
     */
    void setBtnText(List<String> listText);

    /**
     * Set text and image to unit help.
     * 
     * @param panelText is text of unit help.
     * @param pathImg a list of image for unit help.
     */
    void addTextAndIconInUnit(String panelText, List<EngineImage> pathImg);
}
