package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.map.level.Map1;
import thatlevelagain.view.hints.level.Hint1;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 1.
 *
 */
public class Level1 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level1(final GameStateManagerImpl manager) {
        super(manager, new Map1(manager), new Hint1());
        this.setHint(new Hint1());
    }
}
