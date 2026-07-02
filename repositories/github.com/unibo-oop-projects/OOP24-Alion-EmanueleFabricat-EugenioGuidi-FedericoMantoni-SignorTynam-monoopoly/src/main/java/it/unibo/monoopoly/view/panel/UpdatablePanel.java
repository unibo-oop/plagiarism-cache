package it.unibo.monoopoly.view.panel;

import javax.swing.JPanel;

import it.unibo.monoopoly.controller.data.impl.ViewUpdateDTO;

/**
 * Interface for {@link JPanel} to be updated every time a game phase finishes.
 */
public interface UpdatablePanel {

    /**
     * Update the panel to reflect the actual state of the model.
     * 
     * @param updateData contains all the data to update the GUI
     */
    void update(ViewUpdateDTO updateData);

}
