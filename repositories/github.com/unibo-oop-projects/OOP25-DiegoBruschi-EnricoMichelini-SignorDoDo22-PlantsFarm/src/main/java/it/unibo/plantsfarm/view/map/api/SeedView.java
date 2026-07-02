package it.unibo.plantsfarm.view.map.api;

import java.awt.event.ActionListener;

/**
 * Represents the view for selecting seeds to plant.
 * It adapts its size based on the screen resolution.
 */
public interface SeedView {

    /**
     * Adds a plant button to the grid.
     *
     * @param plantName     The name of the plant.
     * @param isDiscovered  If false, the icon will be darkened.
     * @param listener      The action to perform on click.
     */
    void addPlantButton(String plantName, boolean isDiscovered, ActionListener listener);

    /**
     * Attaches a listener to the switch buttons.
     *
     * @param listener The action to perform.
     */
    void addSwitchModeListener(ActionListener listener);

    /**
     * Shows the dialog.
     */
    void show();

    /**
     * Closes the dialog.
     */
    void close();

}
