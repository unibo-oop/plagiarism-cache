package it.unibo.monoopoly.view.state.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.utils.impl.CellGiverListener;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.SelectionCellsPanel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementation of the view for the house building state.
 * Handles the interaction with the user for selecting properties to build
 * houses on.
 */
public class ViewBuildHouseState implements ViewState {

    private final MainView mainView;
    private boolean canBuild;

    /**
     * Constructs the view state for house building.
     * 
     * @param mainView the main view
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewBuildHouseState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Sets whether the player can build houses or not.
     * 
     * @param setter true if the player can build, false otherwise
     */
    @Override
    public void setter(final Boolean setter) {
        this.canBuild = setter;
    }

    /**
     * Displays the user interface for selecting the property to build the house on.
     * 
     * @param data the data related to the user's selection
     */
    @Override
    public void visualize(final DataInput data) {
        if (canBuild) {
            final JPanel interactivePanel = new SelectionCellsPanel(this.mainView.getMainFrame().getHeight(),
                    new CellGiverListener(mainView), intToTextCell(data.cellMap().get()), "su cui comprare una casa", true);
            mainView.setInteractivePanel(interactivePanel);
        } else {
            JOptionPane.showMessageDialog(this.mainView.getMainFrame(), "Non hai proprietà su cui costruire case",
                    "Costruisci Casa", JOptionPane.PLAIN_MESSAGE);
            this.mainView.getControllerState().closeControllerState(new DataBuilderOutputImpl().build());
        }
        // System.out.println(data.toString());
    }

    /**
     * Converts a map of cell indices to a map of cell names and house counts.
     * 
     * @param cellMap the map of cell indices and house counts
     * @return a map of cell names and house counts
     */
    private Map<String, Integer> intToTextCell(final Map<Integer, Integer> cellMap) {
        return cellMap.entrySet().stream()
                .collect(Collectors.toMap(e -> this.mainView.getNameCells().get(e.getKey()), Entry::getValue));
    }
}
