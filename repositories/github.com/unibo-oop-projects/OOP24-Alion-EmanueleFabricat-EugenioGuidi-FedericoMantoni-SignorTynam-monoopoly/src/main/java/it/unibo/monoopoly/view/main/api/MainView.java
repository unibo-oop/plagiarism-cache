package it.unibo.monoopoly.view.main.api;

import java.util.List;

import javax.swing.JPanel;

import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.model.gameboard.api.Cell;
import it.unibo.monoopoly.view.panel.game.InteractivePanel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Represents the main view, contains the frame of the entire game.
 */
public interface MainView extends View {

    /**
     * Gets the istance of the actual {@link ControllerState}.
     * 
     * @return the istance of the actual {@link ControllerState}
     */
    ControllerState getControllerState();

    /**
     * Gets the list of the names of all {@link Cell}s.
     * 
     * @return the list of the names of all {@link Cell}s
     */
    List<String> getNameCells();

    /**
     * change the actual state of the view.
     * 
     * @param state the {@link ViewState} to set
     */
    void setState(ViewState state);

    /**
     * Set the new {@link JPanel} for the {@link InteractivePanel}.
     * 
     * @param panel the new {@link JPanel}.
     */
    void setInteractivePanel(JPanel panel);

    /**
     * Gets the actual {@link ViewState}.
     * 
     * @return the actual {@link ViewState}
     */
    ViewState getViewState();

    /**
     * Update the {@link View}.
     */
    void update();

    /**
     * visualize the end game with the winner.
     * 
     * @param player who won.
     */
    void endGame(String player);

}
