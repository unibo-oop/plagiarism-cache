package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint4;
import thatlevelagain.view.map.level.Map4;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 4.
 *
 */
public class Level4 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level4(final GameStateManagerImpl manager) {
        super(manager, new Map4(manager), new Hint4());
        this.setHint(new Hint4());
    }
}
