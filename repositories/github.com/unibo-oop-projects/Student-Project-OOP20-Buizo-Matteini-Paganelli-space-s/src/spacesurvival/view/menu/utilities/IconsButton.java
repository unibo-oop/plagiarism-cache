package spacesurvival.view.menu.utilities;

import spacesurvival.utilities.path.Icon;

/*
 * Implements enumeration of icons for the menu.
 */
public enum IconsButton {
    /**
     * Icon for start button.
     */
    ICON_START(Icon.START),

    /**
     * Icon for setting button.
     */
    ICON_SETTINGS(Icon.SETTINGS),

    /**
     * Icon for sound button.
     */
    ICON_SOUND(Icon.SOUND),

    /**
     * Icon for help button.
     */
    ICON_HELP(Icon.HELP),

    /**
     * Icon for quit button.
     */
    ICON_QUIT(Icon.QUIT);

    private final String path;

    /**
     * Constructor for creating the icon path.
     * @param path icon.
     */
    IconsButton(final String path) {
        this.path = path;
    }

    /**
     * Get path of icon.
     * @return path of icon.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Return descriptions list of path icon.
     */
    @Override
    public String toString() {
        return "IconsButton{" 
                + "path='" + path + '}';
    }
}
