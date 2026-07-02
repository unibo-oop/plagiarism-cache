package view;

import java.awt.Toolkit;

import model.world.WorldSettings;

public final class ViewSettings {

    /**
     * Represents the size of the game interface.
     */
    public static final double BOARD_SIZE = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.9;

    /**
     * Represents the measure to scale the model objects to be represented in the view.
     */
    public static final double SCALE = BOARD_SIZE / WorldSettings.WORLD_HEIGHT; 

    private ViewSettings() {
    }

}
