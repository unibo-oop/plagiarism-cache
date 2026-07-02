package spacesurvival.view.scoreboard;

import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.utilities.GraphicsText;

import java.util.List;

/**
 * Interface that implements the Scoreboard functionalities.
 */
public interface GUIScoreboard extends GUI, GraphicsText {

    /**
     * Set text a all units help.
     * @param listName a list of text.
     */
    void setTextButtons(List<String> listName);

    /**
     * Set linkAction a back buttonLink.
     * @param mainAction is linkAction a current GUI.
     * @param linkAction for link previous GUI.
     */
    void setBtnBackID(LinkActionGUI mainAction, LinkActionGUI linkAction);
}
