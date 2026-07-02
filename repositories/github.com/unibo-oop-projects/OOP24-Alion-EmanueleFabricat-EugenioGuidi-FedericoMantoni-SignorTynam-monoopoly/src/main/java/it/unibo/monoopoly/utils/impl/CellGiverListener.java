package it.unibo.monoopoly.utils.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.controller.state.api.ControllerState;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.DefaultInteractivePanel;
import it.unibo.monoopoly.view.panel.game.SelectionCellsPanel;

/**
 * {@link ActionListener} used to take a selected cell in an
 * {@link SelectionCellsPanel}.
 */
public class CellGiverListener implements ActionListener {
    private final MainView mainView;
    /**
     * Field use to control if the related button was pressed.
     */
    public static final String NO_CHOICE = "Nessuna scelta";

    /**
     * Constructor of the class.
     * 
     * @param mainView used to call the continueState method of the
     *                 {@link ControllerState}.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public CellGiverListener(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        final var button = (JButton) e.getSource();
        final String cellName = button.getText().split("\n")[0];
        final int cellIndex = mainView.getNameCells().indexOf(cellName);
        this.mainView.setInteractivePanel(new DefaultInteractivePanel(this.mainView.getMainFrame().getHeight()));
        if (NO_CHOICE.equals(cellName)) {
            this.mainView.getMainFrame().requestFocusInWindow();
            mainView.getControllerState().closeControllerState(
                    new DataBuilderOutputImpl().build());
        } else {
            this.mainView.getMainFrame().requestFocusInWindow();
            mainView.getControllerState().closeControllerState(
                    new DataBuilderOutputImpl().selectedCell(cellIndex).build());
        }
    }
}
