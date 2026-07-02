package unibo.exiled.model.menu;

/**
 * Represents commands that can be associated with menu items.
 */
public enum Command {
    /**
     * Command for starting a new game.
     */
    NEW_GAME("new_game"),

    /**
     * Command for closing the menu.
     */
    CLOSE_MENU("close_menu"),

    /**
     * Command for quitting the game.
     */
    QUIT("quit");

    private final String commandString;

    /**
     * Creates a new Command with the specified command string.
     *
     * @param s The command string.
     */
    Command(final String s) {
        this.commandString = s;
    }

    /**
     * Gets the command string associated with the command.
     *
     * @return The command string.
     */
    public String getCommandString() {
        return this.commandString;
    }
}
