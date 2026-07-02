package bzzbomber.controller;

import java.util.List;

import bzzbomber.model.Level;
import bzzbomber.model.Model;
import bzzbomber.model.ResetType;
import bzzbomber.model.Timer;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.Door;
import bzzbomber.model.entities.Entity;
import bzzbomber.model.utilities.Direction;
import bzzbomber.model.utilities.Input;
import bzzbomber.view.menu.ViewManager;

/**
 * Controller of game.
 */
public final class Controller implements ControllerInterface {

    /**
     * Number of total life.
     */
    public static final int NUM_LIFE_TOT = 3;
    /**
     * Number of second in level 1.
     */
    public static final int NUM_SEC_INIT_1 = 120;
    /**
     * Number of second in level 2.
     */
    public static final int NUM_SEC_INIT_2 = 160;
    /**
     * Number of second in level 3.
     */
    public static final int NUM_SEC_INIT_3 = 200;

    private Model model;
    private ViewManager view;
    private Looper looper;
    private Thread thLooper;
    private final KeyManager gamePlayKeyListener;
    private volatile GameState gameState;

    /**
     * Constructor of controller.
     */
    public Controller() {
        this.gameState = GameState.PLAY;
        this.gamePlayKeyListener = KeyManager.getKeyManager();
    }

    @Override
    public void startPlayingGame() {
        this.view.showGame();
        this.view.setKeyListenerOnCurrentView(this.gamePlayKeyListener);

        this.setGamePause(false);
    }

    @Override
    public void startFromMainView() {
        this.view.showMainMenu();
    }

    @Override
    public void updateFrame() {
        this.handleGamePlayViewKeyInput();
        this.model.manageInsects();
        this.model.getBomber().contactWithEntity();
        this.model.manageHealth();
        this.view.draw();
    }

    private void handleGamePlayViewKeyInput() {
        this.gamePlayKeyListener.update();
        for (final Input i : this.gamePlayKeyListener.getInputList()) {
            switch (i) {
            case UP:
                this.model.getBomber().move(Direction.UP, this.model.getAllBlock(), this.model.getAllBombs(),
                        this.model.getAllInsects());
                break;
            case DOWN:
                this.model.getBomber().move(Direction.DOWN, this.model.getAllBlock(), this.model.getAllBombs(),
                        this.model.getAllInsects());
                break;
            case LEFT:
                this.model.getBomber().move(Direction.LEFT, this.model.getAllBlock(), this.model.getAllBombs(),
                        this.model.getAllInsects());
                break;
            case RIGHT:
                this.model.getBomber().move(Direction.RIGHT, this.model.getAllBlock(), this.model.getAllBombs(),
                        this.model.getAllInsects());
                break;
            case SPACE:
                this.model.getBomber().plantBomb(this.model.getAllBombs());
            default:
                break;
            }
        }
    }

    @Override
    public void secPassed() {
        this.model.getTimer().dec();
        this.model.manageBombExplosion();
        this.model.getBomber().decrementImmunity();
    }

    @Override
    public void setModel(final Model model) {
        this.model = model;
    }

    @Override
    public void setView(final ViewManager view) {
        this.view = view;
    }

    @Override
    public Level getCurrentLevel() {
        return this.model.getCurrentLevel();
    }

    @Override
    public int getLevelInt() {
        return this.model.getLevelInt();
    }

    @Override
    public List<Entity> getEntities() {
        return this.model.getEntities();
    }

    @Override
    public BzzBomber getBomber() {
        return this.model.getBomber();
    }

    @Override
    public void setGamePause(final boolean gamePause) {
        if (gamePause) {
            this.gameState = GameState.PAUSE;
        } else {
            this.gameState = GameState.PLAY;
        }
        this.handleGameState();
    }

    @Override
    public void setGameLose(final boolean gameLose) {
        if (gameLose) {
            this.gameState = GameState.LOST;
            this.handleGameState();
        }
    }

    @Override
    public void setGameRelaunch(final boolean gameRelaunch) {
        if (gameRelaunch) {
            this.gameState = GameState.RELAUNCH;
            this.handleGameState();
        }
    }

    @Override
    public void setGameWin(final boolean gameWin) {
        if (gameWin) {
            this.gameState = GameState.WON;
            this.handleGameState();
        }
    }

    @Override
    public void levelChanged() {
        this.view.reset(ResetType.LEVEL_CHANGED);
        this.changeEnemy();

    }

    private void handleGameState() {
        switch (this.gameState) {
        case PLAY:
            this.turnOnLooper();
            System.out.println("GAME IS RUNNING");
            break;
        case PAUSE:
            this.turnOffLooper();
            this.view.getMusicClass().stopAll();
            System.out.println("GAME PAUSED");
            break;
        case LOST:
            this.turnOffLooper();
            System.out.println("GAME FINISHED");
            this.view.getHudPanel().showDialogLoose();
            break;
        case WON:
            this.turnOffLooper();
            System.out.println("YOU WON THE GAME");
            this.view.showWinMenu();
            break;
        case RELAUNCH:
            this.turnOffLooper();
            this.model.reset(ResetType.TOTAL);
            this.view.reset(ResetType.TOTAL);
            this.view.getMusicClass().stopAll();
            System.out.println("RESTART GAME");
        default:
            break;
        }
    }

    private void turnOnLooper() {
        this.gamePlayKeyListener.clearAll();
        if (this.thLooper != null && this.thLooper.isAlive()) {
            try {
                this.thLooper.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.looper = new Looper(this);
        this.looper.setOn();
        this.thLooper = new Thread(this.looper);
        this.thLooper.start();
    }

    private void turnOffLooper() {
        this.gamePlayKeyListener.clearAll();
        this.looper.setOff();
    }

    @Override
    public Timer getTimer() {
        return this.model.getTimer();
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public void changeLife() {
        this.view.getHudPanel().lifeChanged(this.model.getBomber());
    }

    @Override
    public void changeEnemy() {
        this.view.getHudPanel().monsterChanged(this.model.getBomber());
    }

    @Override
    public int getScore() {
        return this.model.getBomber().calculateScore();
    }

    @Override
    public ViewManager getViewManager() {
        return this.view;
    }

    @Override
    public void setDoorVisible() {
        this.model.getCurrentLevel().getEntityManager()
                .addEntity(new Door(this.model.getCurrentLevel().getDoorPositions().get(0)));
    }

    @Override
    public void heroWin() {
        this.view.showWinMenu();
    }

    @Override
    public void showStatistic() {
        this.view.showStatisticMenu();
    }
}
