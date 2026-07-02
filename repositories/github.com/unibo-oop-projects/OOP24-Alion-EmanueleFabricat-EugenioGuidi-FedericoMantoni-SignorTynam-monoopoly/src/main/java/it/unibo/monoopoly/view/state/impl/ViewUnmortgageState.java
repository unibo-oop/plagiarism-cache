package it.unibo.monoopoly.view.state.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.utils.impl.CellGiverListener;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.InteractivePanel;
import it.unibo.monoopoly.view.panel.game.SelectionCellsPanel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ViewState} for the card's phase:
 * to allow the player to decide which property to release the mortgage on.
 */
public class ViewUnmortgageState implements ViewState {
    private final MainView mainView;
    private boolean havePropertyToUnmortgage;

    /**
     * Constructor of the class that sets the field.
     * 
     * @param mainView to visualize the correct panel on that.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewUnmortgageState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     *
     * {@inheritDoc}
     * in this specific case,
     * set the field havePropertyToUnmortgage.
     */

    @Override
    public void setter(final Boolean setter) {
        this.havePropertyToUnmortgage = setter;
    }

    /**
     *
     * {@inheritDoc}
     * in this specific case,
     * if the player has properties to unmortgage,
     * it will set the {@link InteractivePanel} with a panel to choose which
     * property to unmortgage.
     */
    @Override
    public void visualize(final DataInput dataInput) {
        if (this.havePropertyToUnmortgage) {
            final JPanel panel = new SelectionCellsPanel(this.mainView.getMainFrame().getHeight(),
                    new CellGiverListener(this.mainView),
                    intToTextCell(dataInput.cellMap().get()), "da disipotecare", true);
            this.mainView.setInteractivePanel(panel);
        } else {
            this.mainView.getControllerState().closeControllerState(new DataBuilderOutputImpl().build());
        }
    }

    private Map<String, Integer> intToTextCell(final Map<Integer, Integer> cellMap) {
        return cellMap.entrySet().stream()
                .collect(Collectors.toMap(e -> this.mainView.getNameCells().get(e.getKey()), Entry::getValue));
    }
}
