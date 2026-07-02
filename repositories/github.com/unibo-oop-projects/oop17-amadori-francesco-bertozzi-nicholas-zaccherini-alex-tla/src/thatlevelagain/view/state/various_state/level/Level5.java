package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint5;
import thatlevelagain.view.map.level.Map5;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 1.
 *
 */
public class Level5 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level5(final GameStateManagerImpl manager) {
        super(manager, new Map5(manager), new Hint5());
        this.setHint(new Hint5());
    }
}
