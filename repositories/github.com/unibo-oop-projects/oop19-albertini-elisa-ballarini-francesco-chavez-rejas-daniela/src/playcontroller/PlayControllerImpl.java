package playcontroller;

import controls.TetrisKeyListener;
import controls.TetrisKeyListenerImpl;
import factory.EnumFactory;
import gamegraphics.ViewGame;
import gamegraphics.ViewGameImpl;
import gamelogic.GameLogic;
import gamelogic.GameLogicImpl;
import gamemanagement.GameManagement;
import gamemanagement.GameManagementImpl;
import manager.ControllerManager;
import movements.Movements;
import movements.MovementsImpl;
import projection.Projection;
import projection.ProjectionImpl;
import sound.Sound;
import sound.SoundImpl;
import speed.Speed;
import speed.SpeedImpl;

/**
 * This class Implements {@link PlayController}.
 */
public final class PlayControllerImpl implements PlayController {
    private final ControllerManager controllerManager;
    private final Sound sound;
    private final GameLogic game;
    private final Movements movements;
    private final Speed speed;
    private final GameManagement gameManagement;
    private final Projection projection;
    private final TetrisKeyListener controls;
    private final ViewGame view;

    /**
     * Constructor of class PlayControllerImpl.
     * 
     * @param controllerManager is used to get custom pieces and
     *                          the starting level.
     */
    public PlayControllerImpl(final ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
        // create all the objects required for the game implementation
        this.sound = new SoundImpl();
        this.game = new GameLogicImpl(this.controllerManager.getSpeedLevel(), this.controllerManager.getTempCustom());
        this.movements = new MovementsImpl(this.game);
        this.speed = new SpeedImpl(this.game, this.movements);
        this.gameManagement = new GameManagementImpl(this);
        this.projection = new ProjectionImpl(this.game);
        this.controls = new TetrisKeyListenerImpl(this);
        this.view = new ViewGameImpl(this);
        // set view
        this.controls.setView(this.view);
        this.controllerManager.setView(this.view);
        // start the game
        this.gameManagement.startPlay();
    }

    @Override
    public ControllerManager getManager() {
        // stop the timer before changing controller
        this.stop();
        return this.controllerManager;
    }

    @Override
    public void stop() {
        this.gameManagement.stopPlay();
    }

    @Override
    public void resume() {
        this.gameManagement.resumePlay();
    }

    @Override
    public void restart() {
        this.stop();
        // back to the settings menu
        this.controllerManager.setController(EnumFactory.SETTINGS);
    }

    @Override
    public Sound getSound() {
        return this.sound;
    }

    @Override
    public GameLogic getGame() {
        return this.game;
    }

    @Override
    public Movements getMovements() {
        return this.movements;
    }

    @Override
    public Speed getSpeed() {
        return this.speed;
    }

    @Override
    public GameManagement getGameManagement() {
        return this.gameManagement;
    }

    @Override
    public Projection getProjection() {
        return this.projection;
    }

    @Override
    public TetrisKeyListener getControls() {
        return this.controls;
    }

    @Override
    public ViewGame getView() {
        return this.view;
    }
}
