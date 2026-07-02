package starcatraz.controller.game;

import javafx.event.ActionEvent;

/**
 * Controller for RobotAttackView.
 */
public interface RobotAttackController {

    /**
     * Disables the rocket button if there are no active rockets
     * and the chip button if the player's hand contains no chips.
     */
    void disableInappropriateButtons();

    /**
     * Discard the player hand to stop a robot attack.
     * @param event
     */
    void discardHandButtonClick(ActionEvent event);

    /**
     * Discard cards from the deck to stop a robot attack.
     * @param event
     */
    void discardFromDeckButtonClick(ActionEvent event);

    /**
     * Discard a chip to stop a robot attack.
     * @param event
     */
    void useChipButtonClick(ActionEvent event);

    /**
     * Put an active rocket back in the deck to stop a robot attack.
     * @param event
     */
    void useRocketButtonClick(ActionEvent event);
}
