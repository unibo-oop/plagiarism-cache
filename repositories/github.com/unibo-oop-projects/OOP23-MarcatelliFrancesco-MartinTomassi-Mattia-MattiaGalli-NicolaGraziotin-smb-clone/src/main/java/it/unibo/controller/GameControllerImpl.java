package it.unibo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.commons.Constants;
import it.unibo.commons.Point2D;
import it.unibo.model.GameModel;
import it.unibo.model.GameModelImpl;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.statistic.Statistic;
import it.unibo.model.statistic.StatisticImpl;
import it.unibo.model.tiles.Tile;
import it.unibo.view.window.GameWindow;
import it.unibo.view.window.GameWindow.PanelType;
import it.unibo.view.window.factory.GameWindowFactoryImpl;

/**
 * The GameControllerImpl class implements the GameController interface and
 * provides control logic for the game.
 */
public class GameControllerImpl implements GameController {

    private static final int INITIAL_DELAY = 0;
    private static final int FRAME_RATE = 17;
    private final GameModel gameModel;
    private final GameWindow gameWindow;
    private final Timer timer;
    private final Statistic statistic;
    private TimerTask gameLoop;
    private TimerTask gameTimer;
    private int centiSeconds;
    private int currentDeaths;


    /**
     * Constructs a new GameControllerImpl instance.
     * Initializes the game model and the game window.
     */
    public GameControllerImpl() {
        this.timer = new Timer();
        this.statistic = new StatisticImpl();
        this.gameModel = new GameModelImpl(Constants.SOURCE_MAP, this);
        this.gameWindow = new GameWindowFactoryImpl().createSwingGameWindow(this);
    }

    @Override
    public final void startWindow() {
        this.gameWindow.setPanelVisible();
    }

    @Override
    public final void start() {
        this.gameModel.initializeCoords();
        this.gameLoop = createGameLoopTask();
        this.gameTimer = createGameTimerTask();
        timer.schedule(gameLoop, INITIAL_DELAY, FRAME_RATE);
        timer.schedule(gameTimer, INITIAL_DELAY, 10);
        this.newDeathsSession();
    }

    private TimerTask createGameLoopTask() {
        return new TimerTask() {
            @Override
            public void run() {
                gameModel.getCollisionHandler().updateMeatBoy();
                gameModel.getCollisionHandler().check();
                gameWindow.paint(centiSeconds, currentDeaths);
            }
        };
    }

    private TimerTask createGameTimerTask() {
        this.centiSeconds = 0;
        return new TimerTask() {
            @Override
            public void run() {
                centiSeconds++;
            }
        };
    }

    private void newDeathsSession() {
        this.currentDeaths = 0;
    }

    @Override
    public final List<List<Optional<Tile>>> getStationary() {
        return this.gameModel.getStationary();
    }

    @Override
    public final List<CircularSaw> getSaws() {
        return this.gameModel.getSaws();
    }

    @Override
    public final List<Platform> getPlatforms() {
        return this.gameModel.getPlatforms();
    }

    @Override
    public final int getNumRows() {
        return this.gameModel.getNumRows();
    }

    @Override
    public final int getNumCols() {
        return this.gameModel.getNumCols();
    }

    @Override
    public final BandageGirl getBandageGirl() {
        return this.gameModel.getBandageGirl();
    }

    @Override
    public final MeatBoy getMeatBoy() {
        return this.gameModel.getMeatBoy();
    }

    @Override
    public final GameModel getGameModel() {
        return gameModel;
    }

    @SuppressFBWarnings(
        value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR", 
        justification = "The victory method is never called before start, "
                + "so gameloop and gametimer will always be initialized correctly")
    @Override
    public final void victory() {
        this.statistic.updateRecord(centiSeconds);
        this.gameLoop.cancel();
        this.gameTimer.cancel();
        this.gameWindow.displayVictoryMessage();
        this.gameWindow.switchPanel(PanelType.MENU);
    }

    @Override
    public final void isDead() {
        this.currentDeaths++;
        this.statistic.addDeaths();
    }

    @Override
    public final int getDeaths() {
        return this.statistic.getDeaths();
    }

    @Override
    public final int getTimeRecord() {
        return this.statistic.getRecordTime();
    }

    @Override
    public final Point2D<Double, Double> getMeatBoyStartCoord() {
        return this.gameModel.getMeatBoyStartCoord();
    }

    @SuppressFBWarnings(
        value = "UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR", 
        justification = "The esc method is never called before start, "
                + "so gameloop and gametimer will always be initialized correctly")
    @Override
    public final void esc() {
        this.gameLoop.cancel();
        this.gameTimer.cancel();
        this.gameWindow.switchPanel(PanelType.MENU);
    }

    @SuppressFBWarnings("EI_EXPOSE_REP")
    @Override
    public final GameWindow getGameWindow() {
        return gameWindow;
    }

    @Override
    public final void stopMovingMeatBoy(final int keyCode) {
        this.getGameModel().getCollisionHandler().stopMovingMeatBoy(keyCode);
    }

    @Override
    public final void moveMeatBoy(final int keyCode) {
        this.getGameModel().getCollisionHandler().moveMeatBoy(keyCode);
    }
}
