package controller;

import factory.EnumFactory;
import level.Levels;

/**
 * Interface of the controller of the settings to set before start the game.
 */
public interface ControllerSettings extends Controller {

    /**
     * @param type : enumeration that identifies the new type of controller that will take over the application.
     */
    void chageController(EnumFactory type);

    /**
     * @param startLevel : enumeration that identifies the start level from where the user will start to play.
     */
    void setStartLevel(Levels startLevel);

    /**
     * @return true if there is a logged player, false otherwise.
     */
    boolean isPlayerPresent();

    /**
     * Method that sets the list of custom pieces that will be used during the game.
     */
    void setCustomList();

    /** 
     * @return true if there is at least one custom piece to be used during the game, false otherwise.
     */
    boolean checkCustomsPresent();

}
