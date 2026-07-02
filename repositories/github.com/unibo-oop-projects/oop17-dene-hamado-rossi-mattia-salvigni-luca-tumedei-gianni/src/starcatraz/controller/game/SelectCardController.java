package starcatraz.controller.game;

/**
 * Controller for selecting cards revealed by a Chip
 */
public interface SelectCardController {

    /**
     * Setup the view to show the active rockets.
     */
    void setRocketMode();

    /**
     * Setup the view to show the chips in the player's hand.
     */
    void setChipMode();
}
