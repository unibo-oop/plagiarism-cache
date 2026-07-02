package spacesurvival.view.settings;

import spacesurvival.model.EngineImage;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.utilities.GraphicsText;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;

/**
 * Interface that implements the settings functionalities.
 */
public interface GUISettings extends GUI, GraphicsText {

    /**
     * Get buttons of skin component.
     * @return List of JButton.
     */
    List<JButton> getBtnUnitSkin();

    /**
     * Set texts of unit settings.
     * @param listName is a list of text.
     */
    void setUnitsTitle(List<String> listName);

    /**
     * Set the text back button.
     * @param nameBtnBack is text for button.
     */
    void setTextBtnBack(String nameBtnBack);

    /**
     * Set engine image for skin component.
     * @param imageEngine is a model for image's skin.
     */
    void setSkinSpaceShip(EngineImage imageEngine);

    /**
     * Set linkAction a back buttonLink.
     * 
     * @param mainAction is linkAction a current GUI.
     * @param linkAction for link previous GUI.
     */
    void setBtnBackID(LinkActionGUI mainAction, LinkActionGUI linkAction);

    /**
     * Set font for title of unit settings.
     * @param font for title of unit settings.
     */
    void setFontTitleUnit(Font font);

    /**
     * Set transparent component's settings.
     */
    void setTransparentComponent();
}
