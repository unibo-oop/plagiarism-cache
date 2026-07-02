package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for launcher Menu-view.
 */
public class Launcher extends MenuPanel {

    private static final long serialVersionUID = 6835079187244547916L;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public Launcher(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/launcher.png");

        /* BUTTONS ADDING */
        this.addStateButton(new MenuPanel.StateButton("Play", AppState.START),
                new MenuPanel.StateButton("Options", AppState.OPTIONS),
                new MenuPanel.StateButton("Quit", AppState.QUIT));
    }
}