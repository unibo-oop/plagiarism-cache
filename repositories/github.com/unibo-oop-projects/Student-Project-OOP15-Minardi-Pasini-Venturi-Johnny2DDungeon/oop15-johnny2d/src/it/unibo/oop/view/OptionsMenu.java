package it.unibo.oop.view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import it.unibo.oop.controller.AppState;
import it.unibo.oop.controller.StateObserver;
import it.unibo.oop.model.RecordImpl;
import it.unibo.oop.utilities.MusicPlayerImpl;

/**
 * {@link javax.swing.JPanel} for options Menu-view.
 */
public class OptionsMenu extends MenuPanel {

    private static final long serialVersionUID = -4689323418673684324L;

    /**
     * @param stateObs
     *            a {@link StateObserver} object to send "messages".
     */
    public OptionsMenu(final StateObserver stateObs) {
        this.addObserver(stateObs);

        /* ICON SETTING */
        this.setIcon("/options.png");

        /* MUSIC */
        final JLabel label = new JLabel("Music");
        final JCheckBox check = new JCheckBox();
        check.setOpaque(false);
        check.setSelected(MusicPlayerImpl.getInstance().isMusicOn());
        check.addActionListener(e -> MusicPlayerImpl.getInstance().setMusic(check.isSelected()));
        this.addComponents(label, check);

        /* DELETE RECORD-SCORE */
        final JButton reset = new JButton("Reset Record");
        reset.addActionListener(e -> new Thread(() -> RecordImpl.getInstance().reset()).start());
        this.addComponent(reset);

        /* BUTTONS CREATION */
        this.addStateButton(new MenuPanel.StateButton("Credits", AppState.CREDITS),
                new MenuPanel.StateButton("Back", AppState.BACK));
    }
}