package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint3;
import thatlevelagain.view.map.level.Map3;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 3.
 *
 */
public class Level3 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level3(final GameStateManagerImpl manager) {
        super(manager, new Map3(manager), new Hint3());
        this.setHint(new Hint3());
    }
}
