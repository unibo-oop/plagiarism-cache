package thatlevelagain.view.various_jbutton;

import thatlevelagain.view.Save;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * new save button in menu.
 *
 */
public class NewSave extends ButtonGeneral {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "NEW SAVE";
    /**
     * constructor.
     * @param gamePanel 
     *         panel
     * @param level
     *          level
     */
    public NewSave(final GamePanel gamePanel, final LevelStateGeneral level) {
        super(NAME);
        this.addActionListener(e -> {
            Save.newSave(gamePanel, level);
        });
    }
}
