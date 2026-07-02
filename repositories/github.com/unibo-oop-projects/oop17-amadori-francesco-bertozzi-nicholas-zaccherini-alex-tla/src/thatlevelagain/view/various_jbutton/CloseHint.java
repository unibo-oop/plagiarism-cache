package thatlevelagain.view.various_jbutton;

import javax.swing.JDialog;

import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * close menu button in menu.
 *
 */
public class CloseHint extends ButtonGeneral {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "CLOSE WINDOW";
    private final LevelStateGeneral level;
    /**
     * constructor.
     * @param dialog
     *         panel
     * @param level
     *         level 
     */
    public CloseHint(final JDialog dialog, final LevelStateGeneral level) {
        super(NAME);
        this.level = level;
        this.addActionListener(e -> {
            dialog.setVisible(false);
            dialog.dispose();
            this.level.setHintOpen(false);
        });
    }
}
