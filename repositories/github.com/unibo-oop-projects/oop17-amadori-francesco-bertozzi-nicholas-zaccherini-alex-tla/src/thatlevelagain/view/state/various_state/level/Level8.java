package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint8;
import thatlevelagain.view.map.level.Map8;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 8.
 *
 */
public class Level8 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level8(final GameStateManagerImpl manager) {
        super(manager, new Map8(manager), new Hint8());
        this.setHint(new Hint8());
    }
}
