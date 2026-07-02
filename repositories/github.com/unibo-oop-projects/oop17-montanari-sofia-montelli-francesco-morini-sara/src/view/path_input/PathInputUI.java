package view.path_input;

import view.interfaces.BasicView;
import view.interfaces.ConfermableView;

/**
 * Manages a UI that request the user to provide some file paths.
 */
public interface PathInputUI extends BasicView, ConfermableView {
    /**
     * Shows a prompt for choosing the file which contains the player data.
     */
    void choosePlayerFile();

    /**
     * Shows a prompt for choosing the file which contains the statistics data.
     */
    void chooseStatisticsFile();

    /**
     * Use the default value for both of them.
     */
    void useDefault();
}
