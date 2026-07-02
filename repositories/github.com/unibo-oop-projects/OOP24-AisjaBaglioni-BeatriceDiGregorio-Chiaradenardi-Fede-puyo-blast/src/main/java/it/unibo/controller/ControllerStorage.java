package it.unibo.controller;

import java.util.HashSet;

import it.unibo.GameListener;
import it.unibo.controller.interfaces.ControllerStorageInterface;
import it.unibo.model.ModelStorage;
import it.unibo.view.GameView;

public class ControllerStorage implements ControllerStorageInterface{
    public final BulletController bulletController;
    public final CannonController cannonController;
    public final ClickController clickController;
    public final EndController endController;
    public final ExitController exitController;
    public final GameLoop gameLoop;
    public final FreezeController freezeController;
    public final KeyboardController keyboardController;
    public final PauseController pauseController;
    public final ProgressBarController progressBarController;
    public final PuyoDropper puyoDropper;
    public final PuyoExplosionController puyoExplosionController;
    public final ScoreController scoreController;
    public final TryAgainController tryAgainController;

    public ControllerStorage(ModelStorage modelStorage, GameListener gameListener,
            LevelManager.LevelConfig levelConfig) {
        this.gameLoop = new GameLoop(null, modelStorage.pauseModel, modelStorage.statusModel, new HashSet<>());
        this.progressBarController = new ProgressBarController(modelStorage.progressBarModel);
        this.cannonController = new CannonController(modelStorage.cannonModel, modelStorage.keyboardModel,
                progressBarController);
        this.freezeController = new FreezeController(modelStorage.grid);
        this.pauseController = new PauseController(modelStorage.pauseModel);
        this.puyoExplosionController = new PuyoExplosionController(modelStorage.grid);
        this.scoreController = new ScoreController(modelStorage.scoreModel);
        this.endController = new EndController(modelStorage.grid, modelStorage.scoreModel, modelStorage.statusModel);
        this.exitController = new ExitController(gameListener, gameLoop);
        this.tryAgainController = new TryAgainController(levelConfig, gameListener, gameLoop);
        this.puyoDropper = new PuyoDropper(modelStorage.grid, levelConfig);
        this.keyboardController = new KeyboardController(modelStorage.keyboardModel);
        this.bulletController = new BulletController(modelStorage.bulletModel, modelStorage.grid,
                modelStorage.keyboardModel, progressBarController, null, scoreController, modelStorage.scale);
        this.addTickListeners();
        this.clickController = new ClickController(new HashSet<>());
    }


    @Override
    public void addTickListeners() {
        this.gameLoop.addTickListener(this.cannonController);
        this.gameLoop.addTickListener(this.progressBarController);
        this.gameLoop.addTickListener(this.freezeController);
        this.gameLoop.addTickListener(this.puyoExplosionController);
        this.gameLoop.addTickListener(this.endController);
        this.gameLoop.addTickListener(this.puyoDropper);
        this.gameLoop.addTickListener(this.bulletController);
    }

    @Override
    public void linkGameView(GameView gameView) {
        this.gameLoop.setGameView(gameView);
        this.bulletController.setCannonView(gameView.cannonView);
        this.clickController.addClickable(gameView.exitView);
        this.clickController.addClickable(gameView.pauseView);
        this.clickController.addClickable(gameView.tryAgainView);
        gameView.addMouseListener(this.clickController);
        this.gameLoop.addTickListener(gameView.puyoRenderer);
        gameView.addKeyListener(this.keyboardController);
        gameView.addKeyListener(this.pauseController);
    }

    public void start() {
        this.puyoDropper.initialize();
        this.gameLoop.startGame();
    }
}