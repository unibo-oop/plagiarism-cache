package tmw.controller.hud;

import javafx.scene.Parent;

/**
 * Interface that handle the HUD.
 *
 */
public interface HudController {

    /**
     * Display all statistics on screen.
     */
    void draw();

    /**
     * Getter for the Hud.
     * 
     * @return Hud
     */
    Parent getHud();

    /**
     * Update life, score and item owned by the player in the hud.
     */
    void updateHudValue();

    /**
     * Setter for the information label.
     * 
     * @param log the message to be displayed
     */
    void setInfoLabel(String log);

    /**
     * Getter for the information label.
     * 
     * @return the content of the label
     */
    String getInfoLabel();
}
