package model.minigames.colorgama;

import model.minigames.MiniGameSettings;

/**
 * This interface calculates constants according to the difficulty.
 *
 */
public interface ColorGamaSettings extends MiniGameSettings {

    /**
     * Get the base point of minigame.
     *
     * @return point
     *          base point
     */
    int getBasePoint();


    /**
     * Get the number of different color to show.
     * 
     * @return colors
     *          the number of the color
     */
    int getNumColor();
}
