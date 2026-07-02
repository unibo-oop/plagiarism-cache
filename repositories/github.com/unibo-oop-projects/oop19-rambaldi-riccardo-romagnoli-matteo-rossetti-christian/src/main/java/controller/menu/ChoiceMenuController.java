package controller.menu;

import javafx.scene.control.ComboBox;

/**
 * This is the Controller associated to the {@link ChoiceMenuView}. It sets all the buttons' functions and populates
 * the boxes.
 */
public interface ChoiceMenuController {

    /**
     * Starts the game.
     * @param mapChosen
     *      The map chosen by the player.
     * @param name
     *      The name of the player.
     * @param characterChosen
     *      The character chosen by the player
     */
    void startHit(String mapChosen, String name, String characterChosen);

    /**
     * Goes back to the main menu.
     */
    void goBackHit();

    /**
     * Populates the mapBox.
     * @param mapBox
     *      The choice menu's mapBox.
     */
    void setMapBox(ComboBox<String> mapBox);

    /**
     * Populates the characterBox.
     * @param characterBox
     *      The choice menu's characterBox.
     */
    void setCharacterBox(ComboBox<String> characterBox);
}
