package settings.utilities;

/**
 * 
 * Enumeration that contains the standard text of CheckBox.
 *
 */
public enum CheckBoxText {
    /**
     * Text of a standard checked CheckBox.
     */
    CHECKED("ON"),
    /**
     * Text of a checked Training Mode CheckBox.
     */
    TRAININGMODE_CHECKED("ON (Your score will not be saved)"),
    /**
     * Text of a standard unchecked CheckBox.
     */
    UNCHECKED("OFF");

    private final String checkboxText;

    CheckBoxText(final String checkboxText) {
        this.checkboxText = checkboxText;
    }

    /**
     * Provides the text that the Settings CheckBox must display.
     * 
     * @return the CheckBox text.
     */
    public String getText() {
        return this.checkboxText;
    }
}
