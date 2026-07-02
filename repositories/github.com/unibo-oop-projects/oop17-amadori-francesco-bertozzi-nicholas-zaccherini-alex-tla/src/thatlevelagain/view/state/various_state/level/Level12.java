package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint12;
import thatlevelagain.view.map.level.Map12;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 12.
 *
 */
public class Level12 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level12(final GameStateManagerImpl manager) {
        super(manager, new Map12(manager), new Hint12());
        this.setHint(new Hint12());
    }
}
