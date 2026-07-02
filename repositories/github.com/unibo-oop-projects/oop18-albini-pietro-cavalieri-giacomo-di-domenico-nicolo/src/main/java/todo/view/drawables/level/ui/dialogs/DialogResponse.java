package todo.view.drawables.level.ui.dialogs;

/**
 * This enumeration represents the possible responses for a {@link GameDialog}.
 */
public enum DialogResponse {
    /**
     * The user pressed the OK button.
     */
    OK("OK"),
    /**
     * The user pressed the GO BACK TO THE MENU button.
     */
    GO_TO_MENU("Go back to the menu");

    private final String buttonText;

    DialogResponse(final String buttonText) {
        this.buttonText = buttonText;
    }

    @Override
    public String toString() {
        return this.buttonText;
    }
}
