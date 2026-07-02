package it.unibo.oop.view;

import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;

/**
 * {@link javax.swing.JPanel} for quit Menu-view.
 */
public class QuitMenu extends MenuPanel {

    private static final long serialVersionUID = -8073693943984907077L;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public QuitMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/exit.png");

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Yes", AppState.EXIT),
                new MenuPanel.StateButton("No", AppState.BACK));
    }
}