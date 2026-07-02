package it.unibo.monoopoly.view.state.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.common.Event;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.utils.impl.CellGiverListener;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.SelectionCellsPanel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementation of {@link ViewState} that depending on the situation,
 * either notifies the success of the payment,
 * allows the player to choose which property to sell or mortgage,
 * or declares bankruptcy.
 */
public class ViewBankerState implements ViewState {
    private final MainView mainView;
    private boolean isIndebted;

    /**
     * Constructor of the class.
     * 
     * @param mainView to visualize the correct panel on that.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewBankerState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     *
     * {@inheritDoc}
     * in this specific case,
     * set the field isIndebted.
     */
    @Override
    public void setter(final Boolean setter) {
        this.isIndebted = setter;
    }

    /**
     *
     * {@inheritDoc}
     * in this specific case,
     * displays the appropriate dialog or sets the interactive panel based on what
     * the player needs to do.
     */
    @Override
    public void visualize(final DataInput data) {
        if (!isIndebted) {
            JOptionPane.showMessageDialog(this.mainView.getMainFrame(),
                    "Pagamento effetuato con successo", "Pagamento", JOptionPane.PLAIN_MESSAGE);
            this.mainView.getControllerState().closeControllerState(new DataBuilderOutputImpl().build());
        } else {
            switch (data.event().get()) {
                case Event.SELL_HOUSE:
                    final JPanel panel = new SelectionCellsPanel(this.mainView.getMainFrame().getHeight(),
                            new CellGiverListener(this.mainView),
                            intToTextCell(data.cellMap().get()), "in cui vendere una casa", false);
                    this.mainView.setInteractivePanel(panel);
                    break;
                case Event.MORTGAGE_PROPERTY:
                    final JPanel panel1 = new SelectionCellsPanel(this.mainView.getMainFrame().getHeight(),
                            new CellGiverListener(this.mainView),
                            intToTextCell(data.cellMap().get()), "da ipotecare", false);
                    this.mainView.setInteractivePanel(panel1);
                    break;
                case Event.BANKRUPT:
                    JOptionPane.showMessageDialog(this.mainView.getMainFrame(),
                            "HAI FINITO I SOLDI E LE PROPRIETA' SEI IN BANCAROTTA, PER TE IL GIOCO E' FINITO",
                            "Bancarotta", JOptionPane.PLAIN_MESSAGE);
                    this.mainView.getControllerState()
                            .closeControllerState(new DataBuilderOutputImpl().build());
                    break;
                default:
                    throw new IllegalStateException("ViewBankerState doesn't recognize the state in witch it is");
            }
        }
    }

    private Map<String, Integer> intToTextCell(final Map<Integer, Integer> cellMap) {
        return cellMap.entrySet().stream()
                .collect(Collectors.toMap(e -> this.mainView.getNameCells().get(e.getKey()), Entry::getValue));
    }
}
