package view;

import java.awt.Color;
/**
 * colors used inside the program.
 *
 */
public class Colors {
    private static final Color BACKGROUND_COLOR = new Color(220, 237, 200);
    private static final Color DARK_GREEN = new Color(174, 213, 129);
    private static final Color GREENHOUSE_COLOR = new Color(188, 170, 164);
    private static final Color PLANTED_STATUS_COLOR = new Color(255, 202, 40); //cambiare il colore
    private static final Color FREE_STATUS_COLOR = new Color(241, 248, 233);

    /**
     * 
     * @return background color
     */
    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }
    /**
     * 
     * @return dark green
     */
    public static Color getDarkGreen() {
        return DARK_GREEN;
    }
    /**
     * 
     * @return greenhouse color
     */
    public static Color getGreenhouseColor() {
        return GREENHOUSE_COLOR;
    }
    /**
     * 
     * @return planted status color
     */
    public static Color getPlantedStatusColor() {
        return PLANTED_STATUS_COLOR;
    }
    /**
     * 
     * @return free status color
     */
    public static Color getFreeStatusColor() {
        return FREE_STATUS_COLOR;
    }


}
