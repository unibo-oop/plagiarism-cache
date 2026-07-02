package it.unibo.monoopoly.view.state.impl;

import javax.swing.JOptionPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.monoopoly.controller.data.impl.DataInput;
import it.unibo.monoopoly.view.main.api.MainView;
import it.unibo.monoopoly.view.state.api.ViewState;

/**
 * Visualizes the prison state.
 */
public class ViewPrisonState implements ViewState {

    private final MainView mainView;
    private boolean goToJail;

    /**
     * Constructs the view prison state.
     * 
     * @param mainView the main view
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppressing according to pattern State")
    public ViewPrisonState(final MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Sets the mode.
     * 
     * @param mode the mode
     */
    @Override
    public void setter(final Boolean mode) {
        this.goToJail = mode;
    }

    /**
     * Visualizes the state.
     * 
     * @param data the data
     */
    @Override
    public void visualize(final DataInput data) {
        if (goToJail) {
            JOptionPane.showMessageDialog(mainView.getMainFrame(), "Devi andare in prigione senza passare dal VIA!");
        } else {
            JOptionPane.showMessageDialog(mainView.getMainFrame(), "É il turno di " + data.text().get(), "Nuovo turno",
                    JOptionPane.PLAIN_MESSAGE);
            if (data.isEnabled().isPresent()) {
                if (data.isEnabled().get()) {
                    JOptionPane.showMessageDialog(mainView.getMainFrame(),
                            "Sei uscito di prigione usando la tua carta 'Esci Gratis di Prigione'.");
                } else {
                    JOptionPane.showMessageDialog(mainView.getMainFrame(), "Devi uscire di prigione e pagare €50.");
                }
            }
        }
    }
}
