package thatlevelagain.view.various_jbutton;

import thatlevelagain.view.Save;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * existent save button in menu.
 *
 */
public class ExistentSave extends ButtonGeneral {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String NAME = "OVERWRITE SAVE";
    /**
     * constructor.
     * @param panel 
     *         panel
     * @param level 
     *         level
     */
    public ExistentSave(final GamePanel panel, final LevelStateGeneral level) {
        super(NAME);
        this.addActionListener(e -> {
            Save.existentSave(panel, level);
        });
    }
}
