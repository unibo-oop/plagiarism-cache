package it.unibo.oop.view;

import javax.swing.JLabel;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for credits Menu-view.
 */
public class CreditsMenu extends MenuPanel {

    private static final long serialVersionUID = 7436402336801219924L;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public CreditsMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/credits.png");

        /* LABELS ADDING */
        this.addComponents(new JLabel("Model: Matteo Minardi"), new JLabel("View: Giacomo Pasini"),
                new JLabel("Controller: Paolo Venturi"));

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Back", AppState.BACK));
    }
}