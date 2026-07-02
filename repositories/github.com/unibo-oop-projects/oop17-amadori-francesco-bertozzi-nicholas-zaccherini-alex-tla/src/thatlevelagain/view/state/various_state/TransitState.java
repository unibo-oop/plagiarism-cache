package thatlevelagain.view.state.various_state;

import java.awt.Graphics2D;
import java.io.IOException;

import thatlevelagain.view.hints.HintImpl;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;
import thatlevelagain.view.state.various_state.level.Level10;
import thatlevelagain.view.state.various_state.level.Level11;
import thatlevelagain.view.state.various_state.level.Level12;
import thatlevelagain.view.state.various_state.level.Level2;
import thatlevelagain.view.state.various_state.level.Level3;
import thatlevelagain.view.state.various_state.level.Level4;
import thatlevelagain.view.state.various_state.level.Level5;
import thatlevelagain.view.state.various_state.level.Level6;
import thatlevelagain.view.state.various_state.level.Level7;
import thatlevelagain.view.state.various_state.level.Level8;
import thatlevelagain.view.state.various_state.level.Level9;

/**
 * Transit state.
 *
 */
public class TransitState extends GameStateImpl {

    private final int state;

    /***************************/
    /**
     * 
    * @param manager
     *         manager to set.
     * @param map
     *         map that will define level.
     * @param hint
     *         level hint.
     * @param state
     *         game state number
     */
    public TransitState(final GameStateManagerImpl manager, final MapImpl map, final HintImpl hint, final int state) {
        super();

        @SuppressWarnings("unused")
        final MapImpl mapUnused;
        this.setManager(manager);
        this.state = state;
        this.setHint(hint);
        mapUnused = map;
    }
    /**
     * @throws IOException 
     * */

    public void creaLivello() throws IOException {
        for (int i = 0; i <= state - 1; i++) {
            if (i > GameStateManagerImpl.PAUSE_LEVEL) {
                this.getManager().getStates().add(new PauseState(this.getManager(), state - 3));
            }
        }
        this.getManager().getStates().get(GameStateManagerImpl.PAUSE_LEVEL).setLevelIndex(state - 3);
        switch (state) {
        case GameStateManagerImpl.LEVEL2: this.getManager().getStates().add(new Level2(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL3: this.getManager().getStates().add(new Level3(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL4: this.getManager().getStates().add(new Level4(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL5: this.getManager().getStates().add(new Level5(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL6: this.getManager().getStates().add(new Level6(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL7: this.getManager().getStates().add(new Level7(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL8: this.getManager().getStates().add(new Level8(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL9: this.getManager().getStates().add(new Level9(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL10: this.getManager().getStates().add(new Level10(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL11: this.getManager().getStates().add(new Level11(this.getManager()));
                                          break;
        case GameStateManagerImpl.LEVEL12: this.getManager().getStates().add(new Level12(this.getManager()));
                                          break;
        default:
                                          break;
        }
        this.getManager().setState(GameStateManagerImpl.PAUSE_LEVEL);
        try {
            Thread.sleep(GameStateManagerImpl.TIMEWAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.getManager().setState(state);
    }

    @Override
    public void init() { }

    @Override
    public final void draw(final Graphics2D g) { }

    @Override
    public void update() { }

    @Override
    public final void keyPressed(final int k) { }

    @Override
    public void keyReleased(final int k) { }

    @Override
    public void mouseClicked(final int x, final int y) { }

    @Override
    public void mouseEntered(final int x, final int y) { }

    @Override
    public void mouseExited(final int x, final int y) { }

    @Override
    public void mousePressed(final int x, final int y) { }

    @Override
    public void mouseReleased(final int x, final int y) { }
}
