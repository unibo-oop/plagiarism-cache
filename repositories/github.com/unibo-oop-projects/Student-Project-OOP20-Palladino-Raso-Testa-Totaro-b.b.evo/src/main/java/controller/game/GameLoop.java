package controller.game;

import controller.input.ControllerInput;
import controller.input.ControllerInputImpl;
import controller.input.InputEvent;
import controller.input.InputEventImpl;
import controller.leaderboard.LeaderboardController;
import controller.leaderboard.LeaderboardControllerImpl;
import controller.scene.ControllerGame;
import controller.scene.ControllerGameFinal;
import controller.scene.ControllerNextLevel;
import controller.scene.ControllerGameOver;
import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.GameBoard;
import model.leaderboard.StandardScoreSortingStrategy;
import model.mapeditor.LevelStandard;
import model.settings.SettingLevel.SettingLevelBuilder;
import model.settings.SettingLevelManager;
import model.utilities.GameUtilities;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalStyle;
import view.PersonalViews;
import view.SceneLoader;

/**
 * Class that represent the gameloop pattern.
 * Load, update and render the scene.
 */
public class GameLoop implements Runnable {

    /**
     * Use to calculate the fps.
     * 20 ms = 50 fps
     */
    private static final long PERIOD = 20;

    private final Scene scene;
    private final GameState gameState;
    private final GameBoard board;
    private final ControllerGame controllerGame;
    private final ControllerInput inputController;
    private final SettingsController setting = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);

    public GameLoop(final Scene scene) {
        this.scene = scene;
        this.scene.getStylesheets().add(PersonalStyle.DEFAULT_STYLE.getStylePath()); //Apply css to scene
        final Stage currentStage = (Stage) this.scene.getWindow();
        currentStage.setResizable(false); // Don't permise resize
        currentStage.setWidth(ScreenUtilities.SCREEN_WIDTH); // Set new Dimension
        currentStage.setHeight(ScreenUtilities.SCREEN_HEIGHT); // Set new Dimension
        this.gameState = new GameStateImpl();
        this.board = gameState.getBoard();
        this.controllerGame = (ControllerGame) PersonalViews.SCENE_GAME.loadScene(); 
        this.controllerGame.setBackgroundImage(gameState.getLevel().getBackground());
        if (this.setting.isMusicEnable()) {
            SoundController.playMusic(gameState.getLevel().getMusic().getURL());
        }
        this.changeView(PersonalViews.SCENE_GAME);
        this.inputController = new ControllerInputImpl();
        final InputEvent inputEvent = new InputEventImpl(this.controllerGame.getCanvas(), inputController, this.gameState);
            inputEvent.notifyEvent();
    }

    /**
     * Apply the game loop steps based on current game phase.
     */
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!gameState.getStatus().equals(GameStatus.WIN) 
            && !gameState.getStatus().equals(GameStatus.LOST)
            && !gameState.getStatus().equals(GameStatus.MENU)) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            switch (gameState.getStatus()) {
            case START:
                gameState.init();
                break;
            case PAUSE:
                this.controllerGame.setPlay(true);
                this.board.clearPowerUps();
                render();
                break;
            case RUNNING:
                this.controllerGame.setPlay(false);
                processInput();
                updateGame(elapsed);
                render();
                break;
            default:
                break;
            }
            waitForNextFrame(current);
            lastTime = current;
        }
        SoundController.stopMusic();

        //Check current gameState to set next loop
        if (gameState.getStatus().equals(GameStatus.WIN)
                && LevelStandard.isStandardLevel(gameState.getLevel().getLevelName()) 
                && LevelStandard.getSelectionFromLevel(gameState.getLevel()).hasNext()) {
                saveState(GameStatus.WIN);
            changeView(PersonalViews.SCENE_NEXT_LEVEL);
        } else if (gameState.getStatus().equals(GameStatus.WIN) && gameState.getLevel().getLevelName().equals(LevelStandard.LEVEL6.getName())) {
            saveState(GameStatus.LOST);
            changeView(PersonalViews.SCENE_GAME_FINAL);
        } else if (gameState.getStatus().equals(GameStatus.MENU)) {
            changeView(PersonalViews.SCENE_MAIN_MENU);
        } else { 
            //Check for creative mode
            if (GameStateImpl.isCreativeMode() && gameState.getStatus().equals(GameStatus.WIN)) {
                saveState(GameStatus.LOST);
                changeView(PersonalViews.SCENE_GAME_FINAL);
            } else { 
                saveState(GameStatus.LOST);
                changeView(PersonalViews.SCENE_GAME_OVER);
            }
        }
    }

    /**
     * Change the current view with the layout passed.
     * @param layout enum of next scene
     */
    private void changeView(final PersonalViews layout) {
        if (layout.equals(PersonalViews.SCENE_NEXT_LEVEL)) {
            final ControllerNextLevel nextLevelController = (ControllerNextLevel) layout.loadScene();
            nextLevelController.update(gameState.getLevel(), gameState.getPlayer());
        } else if (layout.equals(PersonalViews.SCENE_GAME_OVER) || layout.equals(PersonalViews.SCENE_GAME_FINAL)) {
            final LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
            final StandardScoreSortingStrategy ls = new StandardScoreSortingStrategy(); 

           if (layout.equals(PersonalViews.SCENE_GAME_FINAL)) {
                final ControllerGameFinal controllerGameFinal = (ControllerGameFinal) layout.loadScene();
                controllerGameFinal.updateScore(this.gameState.getPlayerScore(), leaderboard.getPodium(1, ls).toString());
           } else {
                final ControllerGameOver controllerGameOver = (ControllerGameOver) layout.loadScene();
                controllerGameOver.updateScore(this.gameState.getPlayerScore(), leaderboard.getPodium(1, ls).toString());
           }
        } 

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (layout.equals(PersonalViews.SCENE_GAME) || layout.equals(PersonalViews.SCENE_NEXT_LEVEL) || layout.equals(PersonalViews.SCENE_GAME_OVER) || layout.equals(PersonalViews.SCENE_GAME_FINAL)) {
                    scene.setRoot(layout.getLayout());
                } else { 
                    SceneLoader.switchScene((Stage) scene.getWindow(), PersonalViews.SCENE_MAIN_MENU.getURL(), 
                                            PersonalViews.SCENE_MAIN_MENU.getTitleScene(), 
                                            scene.getWidth(), 
                                            scene.getHeight(), 
                                            PersonalStyle.DEFAULT_STYLE.getStylePath());
                   scene.setRoot(layout.getAncLayout());
                }
            }
        });
    }

    /**
     * Save the state of the game.
     * @param phase to set 
     */
    private void saveState(final GameStatus state) {
        final SettingLevelBuilder levelLoader = new SettingLevelBuilder();
        if (state.equals(GameStatus.WIN)) {
            //Load next level
            SettingLevelManager.saveOption(levelLoader.selectLevel(LevelStandard.getSelectionFromLevel(gameState.getLevel())
                                                        .next()
                                                        .getLevel())
                                                        .build());
        } else if (state.equals(GameStatus.LOST)) {
            // Save Ranking
            final LeaderboardController leaderboard = new LeaderboardControllerImpl(GameUtilities.LEADERBOARD_PATH);
            leaderboard.addPlayerInLeaderBoard(gameState.getPlayer());
            leaderboard.saveSortLeaderboard(new StandardScoreSortingStrategy());

            //If lost load the first level, needed if you play multiple time without close the app.
            if (LevelStandard.isStandardLevel(gameState.getLevel().getLevelName())) {
                SettingLevelManager.saveOption(levelLoader.fromSettings(SettingLevelManager.loadOption())
                    .selectLevel(LevelStandard.LEVEL1.getLevel())
                    .build());
            }
        }
    }

    /**
     * Take a little pause to synch with the frame rate.
     * 
     * @param current the execution time before the computational time
     */
    private void waitForNextFrame(final long current) {
        final long timeElapsed = System.currentTimeMillis() - current;
        if (timeElapsed < PERIOD) {
                try {
                    Thread.sleep(PERIOD - timeElapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Update board.
     * @param elapsed
     */
    private void updateGame(final int elapsed) {
        board.getEventHanlder().manageEvent();
        board.updateState(elapsed);
    }

    /**
     * Execute the keyboard command.
     */
    private void processInput() {
        board.movePaddle(inputController);
    }

    /**
     * Draw the board elements on the screen.
     */
    private void render() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controllerGame.render(board.getSceneEntities(), gameState.getPlayerScore(), gameState.getLives(), board.getTypePowerUp());
            }
        });
    }
}
