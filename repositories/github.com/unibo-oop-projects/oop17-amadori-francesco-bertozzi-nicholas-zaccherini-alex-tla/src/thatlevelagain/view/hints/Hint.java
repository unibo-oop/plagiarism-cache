package thatlevelagain.view.hints;

import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * Hint Interface.
 *
 */
public interface Hint {

    /**
     * show a dialog with level 1 hint.
     * @param gamePanel
     *         panel
     * @param level
     *          actual level
     */
    void getHint(GamePanel gamePanel, LevelStateGeneral level);

    /**
     * 
     * @return message that help the user
     */
    String getMessage();
}
