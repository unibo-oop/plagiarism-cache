package thatlevelagain.view.state.various_state;

import java.awt.Graphics2D;
import java.io.IOException;

import thatlevelagain.view.backgrounds.various_background.BackgroundLevel;
import thatlevelagain.view.hints.HintImpl;
import thatlevelagain.view.map.MapImpl;
import thatlevelagain.view.sprite.Impostazioni;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * LevelState's class.
 *
 */
public class LevelStateGeneral extends GameStateImpl {

    private final BackgroundLevel background;
    private final MapImpl map;
    private boolean impOpen;
    private boolean hintOpen;
    private final HintImpl hint;

    /**
     * 
     * @param manager
     *         manager to set.
     * @param map
     *         map that will define level.
     * @param hint
     *         level hint.
     */
    public LevelStateGeneral(final GameStateManagerImpl manager, final MapImpl map, final HintImpl hint) {
        super();
        this.setManager(manager);
        this.background = new BackgroundLevel();
        this.map = map;
        this.hint = hint;
        this.impOpen = false;
        this.hintOpen = false;
        map.creaMappa();
    }
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
     * @throws IOException 
     */
    public LevelStateGeneral(final GameStateManagerImpl manager, final MapImpl map, final HintImpl hint, final int state) throws IOException {
        this(manager, map, hint);
        for (int i = 0; i < state; i++) {
            if (manager.getStates().get(i) == null) {
                this.getManager().getStates().add(new PauseState(this.getManager(), state - 3));
            }
        }
        this.getManager().setState(state - 1);
        try {
            Thread.sleep(GameStateManagerImpl.TIMEWAIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.getManager().setState(state - 1);
    }
    /**********************/
    @Override
    public final void init() { }

    @Override
    public final void draw(final Graphics2D g) {
        background.draw(g);
        getMap().draw(g);
    }

    @Override
    public final void update() {
        this.map.getCat().update();
        this.getMap().getCat().setImm();
        if (this.getMap().isGomitoloDraw()) {
            this.map.getGomitolo().update();
        }
        if (this.getMap().isAgPresent() && !this.getMap().isLights()) {
            this.map.getAccalappiaGatti().update();
        }
    }

    @Override
    public final void keyPressed(final int k) {
        this.getMap().getCat().keyPlayerMovementPressed(k);
    }

    @Override
    public final void keyReleased(final int k) {
        this.getMap().getCat().keyPlayerMovementReleased(k);
    }

    @Override
    public final void mouseClicked(final int x, final int y) {
        final int lampHeight = this.getMap().getLamp().getInitialHeight(), lampX = this.getMap().getLamp().getX(),
                lampWidth = this.getMap().getLamp().getInitialWidth(), lampY = this.getMap().getLamp().getY();
        final int impHeight = this.getMap().getImp().getInitialHeight(), impX = this.getMap().getImp().getX(),
                impWidth = this.getMap().getImp().getInitialWidth(), impY = this.getMap().getImp().getY();
        if ((x <= (lampX + lampWidth) && x >= lampX) && (y <= (lampY + lampHeight) && y >= lampY) && !this.isHintOpen() && !this.impOpen) {
            this.setHintOpen(true);
            hint.getHint(this.getManager().getGamePanel(), this);
        } else if ((x <= (impX + impWidth) && x >= impX) && (y <= (impY + impHeight) && y >= impY) && !this.isImpOpen() && !this.hintOpen) {
            this.setImpOpen(true);
            Impostazioni.menu(this.getManager().getGamePanel(), this);
        }
    }

    @Override
    public final void mouseEntered(final int x, final int y) { }

    @Override
    public final void mouseExited(final int x, final int y) { }

    @Override
    public final void mousePressed(final int x, final int y) {
        final int lampHeight = this.getMap().getLamp().getInitialHeight(), lampX = this.getMap().getLamp().getX(),
                lampWidth = this.getMap().getLamp().getInitialWidth(), lampY = this.getMap().getLamp().getY();
        final int impHeight = this.getMap().getImp().getInitialHeight(), impX = this.getMap().getImp().getX(),
                impWidth = this.getMap().getImp().getInitialWidth(), impY = this.getMap().getImp().getY();
        boolean lampPressed = false, impPressed = false;
        if ((x <= (lampX + lampWidth) && x >= lampX) && (y <= (lampY + lampHeight) && y >= lampY)) {
            lampPressed = true;
        }
        this.getMap().getLamp().bigImage(lampPressed);
        if ((x <= (impX + impWidth) && x >= impX) && (y <= (impY + impHeight) && y >= impY)) {
            impPressed = true;
        }
        this.getMap().getImp().bigImage(impPressed);
    }

    @Override
    public final void mouseReleased(final int x, final int y) {
        final int lampHeight = this.getMap().getLamp().getInitialHeight(), lampX = this.getMap().getLamp().getX(),
                lampWidth = this.getMap().getLamp().getInitialWidth(), lampY = this.getMap().getLamp().getY();
        final int impHeight = this.getMap().getImp().getInitialHeight(), impX = this.getMap().getImp().getX(),
                impWidth = this.getMap().getImp().getInitialWidth(), impY = this.getMap().getImp().getY();
        boolean lampPressed = false, impPressed = false;
        if ((x <= (lampX + lampWidth) && x >= lampX) && (y <= (lampY + lampHeight) && y >= lampY)) {
            lampPressed = false;
        }
        this.getMap().getLamp().bigImage(lampPressed);
        if ((x <= (impX + impWidth) && x >= impX) && (y <= (impY + impHeight) && y >= impY)) {
            impPressed = false;
        }
        this.getMap().getImp().bigImage(impPressed);
    }

    /**
     * 
     * @return
     *         map
     */
    public MapImpl getMap() {
        return map;
    }

    /**
     * 
     * @return
     *          if dialog is open.
     */
    public boolean isImpOpen() {
        return this.impOpen;
    }
    /**
     * 
     * @param impOpen
     *           set impOpen
     */
    public void setImpOpen(final boolean impOpen) {
        this.impOpen = impOpen;
    }
    /**
     * 
     * @return
     *          if dialog is open.
     */
    public boolean isHintOpen() {
        return this.hintOpen;
    }
    /**
     * 
     * @param hintOpen
     *           set hintOpen
     */
    public void setHintOpen(final boolean hintOpen) {
        this.hintOpen = hintOpen;
    }
}
