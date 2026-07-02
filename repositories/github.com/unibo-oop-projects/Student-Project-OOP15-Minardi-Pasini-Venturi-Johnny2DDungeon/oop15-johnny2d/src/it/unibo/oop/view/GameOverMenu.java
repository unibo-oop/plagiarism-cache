package it.unibo.oop.view;

import javax.swing.JLabel;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.model.GameStateImpl;
import it.unibo.oop.model.Record;
import it.unibo.oop.model.RecordImpl;

/**
 * {@link javax.swing.JPanel} for pause GameOver-view.
 */
public class GameOverMenu extends MenuPanel {

    private static final long serialVersionUID = 5096699286815303104L;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public GameOverMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/gameover.png");

        /* SCORE LABEL */
        final Record record = RecordImpl.getInstance();
        String mex = "Record " + record.getValue();
        if (record.isRecord()) {
            mex = record.isRecord() ? "Congratulations! New record!" : "";
        }
        final JLabel label = new JLabel(GameStateImpl.getInstance().getScore().toString());
        final JLabel recordLab = new JLabel(mex);
        this.addComponents(label, recordLab);

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Replay", AppState.START),
                new MenuPanel.StateButton("Options", AppState.OPTIONS),
                new MenuPanel.StateButton("Quit", AppState.QUIT));
    }
}