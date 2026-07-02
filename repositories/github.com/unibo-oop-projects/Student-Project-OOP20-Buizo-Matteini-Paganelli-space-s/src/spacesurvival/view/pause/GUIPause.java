package spacesurvival.view.pause;

import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.utilities.ButtonLink;
import spacesurvival.view.utilities.GraphicsText;

import java.awt.Color;
import java.util.List;

public interface GUIPause extends GUI, GraphicsText {

    /**
     * Get ButtonLink from index.
     * 
     * @param ind for index.
     * @return ButtonLink from index.
     */
    ButtonLink getActionBtn(int ind);

    /**
     * Set text all buttons.
     * 
     * @param listText a list of text.
     */
    void setTextButtons(List<String> listText);

    /**
     * Set LinkActionGUI a all button.
     * 
     * @param mainAction of current GUI.
     * @param links a links of other GUI.
     */
    void setLinkActionButtons(LinkActionGUI mainAction, List<LinkActionGUI> links);

    /**
     * Set backgrouds's buttons.
     * @param color that set background buttons.
     */
    void setBackgroundButtons(Color color);

}
