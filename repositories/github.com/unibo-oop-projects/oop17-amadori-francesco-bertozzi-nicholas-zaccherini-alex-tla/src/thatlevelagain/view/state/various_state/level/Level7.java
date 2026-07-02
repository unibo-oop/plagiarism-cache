package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint7;
import thatlevelagain.view.map.level.Map7;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 7.
 *
 */
public class Level7 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level7(final GameStateManagerImpl manager) {
        super(manager, new Map7(manager), new Hint7());
        this.setHint(new Hint7());
    }
}
