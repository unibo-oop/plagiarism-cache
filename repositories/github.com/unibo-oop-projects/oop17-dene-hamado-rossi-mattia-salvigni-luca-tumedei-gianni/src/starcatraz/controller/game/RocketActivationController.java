package starcatraz.controller.game;

import javafx.event.ActionEvent;
import starcatraz.model.cards.Card;

/**
 * Controller for RocketActivationView.
 */
public interface RocketActivationController {

    /**
     * Sets the rocket at the center of the window.
     * @param card
     */
    void setRocket(Card card);

    /**
     * Confirms the rocket activation.
     * @param event
     */
    void activateButtonClick(ActionEvent event);

    /**
     * Cancel the rocket activation.
     * @param event
     */
    void cancelButtonClick(ActionEvent event);
}
