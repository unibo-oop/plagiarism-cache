package thatlevelagain.view.state.various_state.level;


import thatlevelagain.view.hints.level.Hint2;
import thatlevelagain.view.map.level.Map2;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 2.
 *
 */
public class Level2 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level2(final GameStateManagerImpl manager) {
        super(manager, new Map2(manager), new Hint2());
        this.setHint(new Hint2());
    }
}
