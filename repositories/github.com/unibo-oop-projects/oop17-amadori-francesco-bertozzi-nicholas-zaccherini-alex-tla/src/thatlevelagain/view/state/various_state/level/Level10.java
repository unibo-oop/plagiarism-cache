package thatlevelagain.view.state.various_state.level;


import thatlevelagain.view.hints.level.Hint10;
import thatlevelagain.view.map.level.Map10;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.LevelStateGeneral;

/**
 * 
 * class for level 10.
 *
 */
public class Level10 extends LevelStateGeneral {

    /**
     * @param manager
     *         manager to set.
     */
    public Level10(final GameStateManagerImpl manager) {
        super(manager, new Map10(manager), new Hint10());
        this.setHint(new Hint10());
    }
}
