package utilities.enumerations;

/**
 * Enumeration of the icons.
 */
public enum IconType {
    /**
     * Info icon.
     */
    INFO("info"),

    /**
     * Error icon.
     */
    ERROR("error"),

    /**
     * Yes icon.
     */
    YES("yes"),

    /**
     * Game Over icon.
     */
    GAMEOVER("game over"),

    /**
     * Winner icon.
     */
    WINNER("winner");

    private final String name;

    IconType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns the icon image path.
     * 
     * @return the icon image path
     */
    public String getIconPath() {
        final String path = "/icons/" + this.name.replace('.', ' ') + ".png";
        return path.replaceAll(" ", "");
    }

    /**
     * Returns the background image of the dialog associated to the icon.
     * 
     * @return the background color
     */
    public String getBackgound() {
        switch (this) {
        case INFO:
            return "D0EF72";
        case ERROR:
            return "F79398";
        case YES:
            return "FFD064";
        case GAMEOVER:
            return "9286FB";
        case WINNER:
            return "B0ECFF";
        default:
            return "";
        }
    }
};