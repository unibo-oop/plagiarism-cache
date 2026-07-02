package thatlevelagain.view.state.various_state.level;


import thatlevelagain.view.hints.level.Hint11;
import thatlevelagain.view.map.level.Map11;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 11.
 *
 */
public class Level11 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level11(final GameStateManagerImpl manager) {
        super(manager, new Map11(manager), new Hint11());
        this.setHint(new Hint11());
    }
}
