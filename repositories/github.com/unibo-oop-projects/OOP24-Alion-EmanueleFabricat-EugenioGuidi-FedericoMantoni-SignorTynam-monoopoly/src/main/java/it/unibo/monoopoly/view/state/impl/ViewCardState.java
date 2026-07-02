package it.unibo.monoopoly.view.state.impl;

import javax.swing.JOptionPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Implementations of {@link ViewState} for the card's phase:
 * that visualize the text of the card.
 */
public class ViewCardState implements ViewState {
    private final MainView mainView;

    /**
     * Constructor of the class that sets the field.
     * 
     * @param mainView to visualize the correct panel on that.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewCardState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     *
     * {@inheritDoc}
     * in this specific case,
     * is useless.
     */
    @Override
    public void setter(final Boolean setter) {
    }

    /**
     *
     * {@inheritDoc}
     * in this specific case,
     * display the card text.
     */
    @Override
    public void visualize(final DataInput data) {
        JOptionPane.showMessageDialog(this.mainView.getMainFrame(), data.text().get(), "Carte",
                JOptionPane.PLAIN_MESSAGE);
    }

}
