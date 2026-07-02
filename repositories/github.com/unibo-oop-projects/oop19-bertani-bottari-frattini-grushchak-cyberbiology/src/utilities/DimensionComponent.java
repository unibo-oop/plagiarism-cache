package utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Enumeration of the dimensions of the different graphic components of the program.
 *
 */
public enum DimensionComponent {

    /**
     * Contains the width and height value of the language buttons menu.
     */
    BUTTON_LANGUAGE(10, 8),
    /**
     * Contains the width and height value of the changed panel .
     */
    CHANGED_LANGUAGE(11.6, 3.2),
    /**
     * Contains the width and height value of the changed filter .
     */
    CHANGED_FILTER(11.6, 9),
    /**
    /**
     * Contains the width and height value of the clock display.
     */
    CLOCK_DISPLAY(9, 11),
    /**
     * Contains the width and height value of the color button.
     */
    COLOR_CHOOSE_BUTTON(40, 35),
    /**
     * Contains the width and height value of the ColorChoose frame.
     */
    COLOR_CHOOSE_FRAME(3, 7),
    /**
     * Contains the width and height value of the color control button.
     */
    CONTROL_COLOR_BUTTON(60, 35),
    /**
     * Contains the width and height value of the DataMenu frame.
     */
    DATA_MENU_FRAME(3, 1.2),
    /**
     * Contains the width and height value of the LanguageMenu frame.
     */
    LANGUAGE_MENU_FRAME(4, 3),
    /**
     * Contains the width and height value of the Logo image.
     */
    LOGO_IMAGE(44, 3),
    /**
     * Contains the width and height value of the MenuDescription panel.
     */
    MENU_DESCRIPTION_PANEL(3, 26),
    /**
     * Contains the width and height value of the screen.
     */
    SCREEN(1, 1),
    /**
     * Contains the width and height value of the color control button.
     */
    SPECIFIC_DISPLAY(8, 2),
    /**
     * Contains the width and height value of the World frame.
     */
    WORLD_FRAME(1.2, 1.1);

    private Dimension size;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    DimensionComponent(final double width, final double height) { 
        this.size = new Dimension((int) (screenSize.getWidth() / width), (int) (screenSize.getHeight() / height));
    }

    public Dimension getDimension() {
        return size;
    }

}
