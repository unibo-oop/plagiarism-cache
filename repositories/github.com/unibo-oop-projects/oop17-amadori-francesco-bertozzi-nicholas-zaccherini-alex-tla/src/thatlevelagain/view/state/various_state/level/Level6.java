package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint6;
import thatlevelagain.view.map.level.Map6;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 6.
 *
 */
public class Level6 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level6(final GameStateManagerImpl manager) {
        super(manager, new Map6(manager), new Hint6());
        this.setHint(new Hint6());
    }
}
