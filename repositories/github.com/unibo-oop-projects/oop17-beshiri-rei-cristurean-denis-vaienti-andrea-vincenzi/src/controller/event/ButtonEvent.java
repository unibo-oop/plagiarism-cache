package controller.event;

/**
 *Event created by pressing a view button.
 */
public interface ButtonEvent extends Event {

    /**
     * Get the player's name.
     * @return the player's name.
     */
    String getPlayerName();
}
