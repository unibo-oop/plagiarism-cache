package thatlevelagain.view.state.various_state.level;

import thatlevelagain.view.hints.level.Hint9;
import thatlevelagain.view.map.level.Map9;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 9.
 *
 */
public class Level9 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level9(final GameStateManagerImpl manager) {
        super(manager, new Map9(manager), new Hint9());
        this.setHint(new Hint9());
    }
}
