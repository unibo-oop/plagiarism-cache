package unibo.exiled.model.menu;

/**
 * Represents a menu item with associated text and command.
 */
public class MenuItem {
    private final Command itemCommand;
    private String itemText;

    /**
     * Constructs a MenuItem with the specified text and command.
     *
     * @param itemText    The text of the menu item.
     * @param itemCommand The command associated with the menu item.
     */
    public MenuItem(final String itemText, final Command itemCommand) {
        this.itemText = itemText;
        this.itemCommand = itemCommand;
    }

    /**
     * Gets the text of the menu item.
     *
     * @return The text of the menu item.
     */
    public String getItemText() {
        return this.itemText;
    }

    /**
     * Sets the text of the menu item.
     *
     * @param text The new text for the menu item.
     */
    public void setItemText(final String text) {
        this.itemText = text;
    }

    /**
     * Gets the command associated with the menu item.
     *
     * @return The command associated with the menu item.
     */
    public Command getItemCommand() {
        return this.itemCommand;
    }
}
