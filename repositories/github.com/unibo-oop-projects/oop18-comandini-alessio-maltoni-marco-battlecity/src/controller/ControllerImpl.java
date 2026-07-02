package controller;

import java.util.ArrayList;
import java.util.List;

import controller.file.utilities.ResourceManager;
import controller.input.InputController;
import controller.input.KeyboardInputController;
import core.GameLoop;
import core.GameLoopImpl;
import core.GameStatus;
import enums.GameMode;
import enums.GameScene;
import enums.KeyInput;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.entities.GameEntity;
import view.JavaFXView;
import view.View;

/**
 * Implementation of the game's controller. Implements the Controller interface.
 */
public final class ControllerImpl implements Controller {

    private static final int INITIAL_HI_SCORE = 20000;

    private enums.Stage currentStage;
    private GameLoop engine;
    private GameMode mode;
    private final Stage myPrimaryStage;
    private View view;
    private Thread gameThread;
    private boolean gameRunning;
    private List<InputController> inputControllers;
    private GameStatus gameStatus;
    private int myHiScore;
    private int hiScore;

    /**
     * Constructor method for the Controller.
     * 
     * @param primaryStage the stage of the game.
     */
    public ControllerImpl(final Stage primaryStage) {
        this.myPrimaryStage = primaryStage;
    }

    @Override
    public GameMode getGameMode() {
        return mode;
    }

    @Override
    public enums.Stage getCurrentStage() {
        return currentStage;
    }

    @Override
    public int getCurrentStageNumber() {
        return this.currentStage.getStageNumber();
    }

    @Override
    public void init() {
        ResourceManager.getInstance().loadAllFiles();
        view = new JavaFXView(myPrimaryStage, this);
        mode = GameMode.INDEFINITE;
        gameThread = null;
        gameRunning = false;
        inputControllers = null;
        gameStatus = null;
        currentStage = this.getMinStage();
        myHiScore = 0;
        hiScore = INITIAL_HI_SCORE;
        view.initWindow();
        this.setGameScene(GameScene.MAIN_MENU_NEW);
        view.showWindow();
    }

    @Override
    public void setGameMode(final GameMode mode) {
        this.mode = mode;
    }

    @Override
    public void setGameScene(final GameScene scene) {
        if (scene.equals(GameScene.MAIN_MENU_NEW) || scene.equals(GameScene.MAIN_MENU_OLD)) {
            this.setGameMode(GameMode.INDEFINITE);
            this.setStage(enums.Stage.LEVEL_01);
        }
        view.loadScene(scene);
    }

    @Override
    public void setStage(final enums.Stage stage) {
        this.currentStage = stage;
    }

    @Override
    public void startGame() {
        setInputControllers();
        gameRunning = true;
        engine = new GameLoopImpl(this);
        engine.setup();
        gameThread = new Thread((GameLoopImpl) engine);
        gameThread.setName("GameLoop Thread");
        gameThread.start();
    }

    @Override
    public void renderScene(final List<GameEntity> gameEntities) {
        view.renderCurrentScene(gameEntities);
    }

    @Override
    public void stopGame() {
        gameRunning = false;
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
            gameThread = null;
        }
        engine = null;
        inputControllers = null;
    }

    @Override
    public boolean isGameRunning() {
        return gameRunning;
    }

    @Override
    public void setNextStage() {
        final int n = this.currentStage.getStageNumber() + 1;
        for (final enums.Stage s : enums.Stage.values()) {
            if (s.getStageNumber() == n) {
                this.currentStage = s;
                break;
            }
        }
    }

    @Override
    public void setPreviousStage() {
        final int n = this.currentStage.getStageNumber() - 1;
        for (final enums.Stage s : enums.Stage.values()) {
            if (s.getStageNumber() == n) {
                this.currentStage = s;
                break;
            }
        }
    }

    @Override
    public enums.Stage getMaxStage() {
        enums.Stage max = getMinStage();
        for (final enums.Stage s : enums.Stage.values()) {
            if (max.getStageNumber() < s.getStageNumber()) {
                max = s;
            }
        }
        return max;
    }

    @Override
    public enums.Stage getMinStage() {
        return enums.Stage.LEVEL_01;
    }

    @Override
    public void notifyKeyPressedEvent(final KeyInput keyPressed) {
        if ((this.mode.equals(GameMode.ONE_PLAYER) || this.mode.equals(GameMode.TWO_PLAYER))
                && (inputControllers != null) && (inputControllers.size() == 1 || inputControllers.size() == 2)) {
            ((KeyboardInputController) inputControllers.get(0)).notifyKeyPressed(keyPressed);
        }
        if (this.mode.equals(GameMode.TWO_PLAYER) && inputControllers != null && inputControllers.size() == 2) {
            ((KeyboardInputController) inputControllers.get(1)).notifyKeyPressed(keyPressed);
        }
    }

    @Override
    public void notifyKeyReleasedEvent(final KeyInput keyReleased) {
        if ((this.mode.equals(GameMode.ONE_PLAYER) || this.mode.equals(GameMode.TWO_PLAYER))
                && (inputControllers != null) && (inputControllers.size() == 1 || inputControllers.size() == 2)) {
            ((KeyboardInputController) inputControllers.get(0)).notifyKeyReleased(keyReleased);
        }
        if (this.mode.equals(GameMode.TWO_PLAYER) && inputControllers != null && inputControllers.size() == 2) {
            ((KeyboardInputController) inputControllers.get(1)).notifyKeyReleased(keyReleased);
        }
    }

    @Override
    public List<InputController> getInputControllers() {
        return this.inputControllers;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void setGameStatus(final GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void notifyLose() {
        this.stopGame();
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.setGameScene(GameScene.GAME_OVER));
        }
    }

    @Override
    public void notifyWin() {
        this.stopGame();
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> this.setGameScene(GameScene.POINTS_RECAP));
        }
    }

    @Override
    public int getMyHiScore() {
        return this.myHiScore;
    }

    @Override
    public int getHiScore() {
        return this.hiScore;
    }

    @Override
    public void setMyHiScore(final int myHiScore) {
        if (this.myHiScore < myHiScore) {
            this.myHiScore = myHiScore;
        }
    }

    @Override
    public void setHiScore(final int hiScore) {
        if (this.hiScore < hiScore) {
            this.hiScore = hiScore;
        }
    }

    /*
     * Method that sets the input controllers of the players.
     */
    private void setInputControllers() {
        this.inputControllers = new ArrayList<InputController>();
        if (this.mode.equals(GameMode.ONE_PLAYER)) {
            inputControllers.add(new KeyboardInputController(KeyInput.UP_PLAYER_ONE, KeyInput.DOWN_PLAYER_ONE,
                    KeyInput.LEFT_PLAYER_ONE, KeyInput.RIGHT_PLAYER_ONE, KeyInput.FIRE_PLAYER_ONE));
        } else if (this.mode.equals(GameMode.TWO_PLAYER)) {
            inputControllers.add(new KeyboardInputController(KeyInput.UP_PLAYER_ONE, KeyInput.DOWN_PLAYER_ONE,
                    KeyInput.LEFT_PLAYER_ONE, KeyInput.RIGHT_PLAYER_ONE, KeyInput.FIRE_PLAYER_ONE));
            inputControllers.add(new KeyboardInputController(KeyInput.UP_PLAYER_TWO, KeyInput.DOWN_PLAYER_TWO,
                    KeyInput.LEFT_PLAYER_TWO, KeyInput.RIGHT_PLAYER_TWO, KeyInput.FIRE_PLAYER_TWO));
        }
    }

}
