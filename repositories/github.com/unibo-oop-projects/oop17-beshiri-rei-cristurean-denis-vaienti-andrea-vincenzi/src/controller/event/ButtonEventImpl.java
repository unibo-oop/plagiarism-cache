package controller.event;

import controller.utility.ButtonType;

/**
 *Event created by pressing a view button.
 */
public class ButtonEventImpl implements ButtonEvent {

    private final ButtonType buttonType;
    private final String name;

    /**
     * The class constructor.
     * @param type is what the button do.
     * @param name of the player.
     */
    public ButtonEventImpl(final ButtonType type, final String name) {
        this.buttonType = type;
        this.name = name;
    }

    /**
     * Get the button pressed.
     */
    @Override
    public String getEvent() {
        return buttonType.toString();
    }

    /**
     * Get player's name.
     */
    @Override
    public String getPlayerName() {
        return this.name;
    }
}
