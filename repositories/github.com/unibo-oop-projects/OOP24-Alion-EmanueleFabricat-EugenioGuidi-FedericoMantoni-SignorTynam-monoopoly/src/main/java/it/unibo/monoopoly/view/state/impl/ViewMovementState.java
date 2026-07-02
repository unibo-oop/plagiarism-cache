package it.unibo.monoopoly.view.state.impl;

import javax.swing.JOptionPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.utils.impl.RollDicesListener;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.panel.game.RollDicesPanel;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ViewState} for the movement's phase:
 * let the user roll the dices and visualize their result.
 */
public class ViewMovementState implements ViewState {

    private final MainView mainView;

    /**
     * Constructor of the class that sets the field.
     * 
     * @param mainView the main view of the application
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewMovementState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setter(final Boolean setter) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visualize(final DataInput dataInput) {
        if (dataInput.isEnabled().isPresent() && dataInput.isEnabled().get()) {
            this.mainView.setInteractivePanel(new RollDicesPanel(new RollDicesListener(this.mainView),
                    mainView.getMainFrame().getHeight()));
        } else if (dataInput.dices().isPresent()) {
            JOptionPane.showMessageDialog(this.mainView.getMainFrame(),
                    "Primo dado: " + dataInput.dices().get().getLeft()
                            + "\nSecondo dado: " + dataInput.dices().get().getRight(),
                    null, JOptionPane.PLAIN_MESSAGE);
        }
    }
}
