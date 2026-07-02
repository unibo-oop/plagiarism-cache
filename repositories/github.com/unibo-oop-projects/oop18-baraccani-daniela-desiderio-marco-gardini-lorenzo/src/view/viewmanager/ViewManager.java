package view.viewmanager;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import controller.score.Score;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.View;
import view.audio.AudioManager;
import view.controller.CharacterController;
import view.controller.CreditsController;
import view.controller.GameOverController;
import view.controller.MenuController;
import view.controller.PauseController;
import view.controller.ScoreboardController;
import view.controller.SettingsController;
import view.controller.StoryController;
import view.controller.VictoryController;
import view.gameview.GameView;
import view.gameview.GameViewImpl;

/**
 * Class that have to load all scenes into the View. Also It controls any
 * exceptions.
 */
public class ViewManager {

    private final View view;
    private final Stage stage;
    private final AudioManager audioManager;
    private Optional<GameView> gameView;

    /**
     * Create the ViewManager.
     * 
     * @param view         for adding the scenes with the method
     *                     {@link View.setScene()}
     * @param stage        for GameView
     * @param audioManager for {@link SettingsController}
     */
    public ViewManager(final View view, final Stage stage, final AudioManager audioManager) {
        this.gameView = Optional.empty();
        this.view = Objects.requireNonNull(view);
        this.stage = Objects.requireNonNull(stage);
        this.audioManager = Objects.requireNonNull(audioManager);
    }

    /**
     * Return the main {@link View} for handling IO errors.
     * 
     * @return the main {@link View}
     */
    public View getView() {
        return this.view;
    }

    /**
     * Open the Menu scene in the View.
     */
    public void openMenuScene() {
        this.loadScene(SceneFXML.MENU, new MenuController(this, this.audioManager));
    }

    /**
     * Open the Settings scene in the View.
     */
    public void openSettingsScene() {
        this.loadScene(SceneFXML.SETTINGS, new SettingsController(this, this.audioManager));
    }

    /**
     * Open the Credits scene in the View.
     */
    public void openCreditsScene() {
        this.loadScene(SceneFXML.CREDITS, new CreditsController(this));
    }

    /**
     * Open the Scoreboard scene in the View.
     */
    public void openScoreboardScene() {
        try {
            this.loadScene(SceneFXML.SCOREBOARD, new ScoreboardController(this));
        } catch (IOException e) {
            this.view.getController().handleError("Can't open scoreboard", true);
        }
    }

    /**
     * Open the Pause scene in the View.
     */
    public void openPauseScene() {
        this.loadScene(SceneFXML.PAUSE, new PauseController(this, this.audioManager));
    }

    /**
     * Open the Story scene in the View.
     */
    public void openStoryScene() {
        this.loadScene(SceneFXML.STORY, new StoryController(this));
    }

    /**
     * Open the Game scene in the View.
     * 
     * @param playerName for starting the new game.
     */
    public void openGameScene(final String playerName) {
        this.gameView = Optional.of(new GameViewImpl(this, stage, this.audioManager, playerName));
        this.loadScene(SceneFXML.GAME, this.gameView.get());
    }

    /**
     * Reopen the Game scene in the View. Requires that openGameScene has been
     * called before.
     */
    public void reopenGameScene() {
        if (!this.gameView.isPresent()) {
            this.view.getController().handleError("Can't reopen game scene because it was never opened before", true);
        } else {
            this.loadScene(SceneFXML.GAME, this.gameView.get());
        }
    }

    /**
     * Open the GameOver scene in the View.
     * 
     * @param player the player's informations.
     */
    public void openGameOverScene(final Score player) {
        this.loadScene(SceneFXML.GAMEOVER, new GameOverController(this, player, this.audioManager));
    }

    /**
     * Open the Victory scene in the View.
     * 
     * @param player the player's informations.
     */
    public void openVictoryScene(final Score player) {
        this.loadScene(SceneFXML.VICTORY, new VictoryController(this, player, this.audioManager));
    }

    /**
     * Open the Character scene in the View.
     */
    public void openCharacterScene() {
        this.loadScene(SceneFXML.CHARACTER, new CharacterController(this));
    }

    /**
     * @return the GameView
     */
    public GameView getGameView() {
        return this.gameView.get();
    }

    /*
     * Load the scene in the View
     */
    private void loadScene(final SceneFXML sceneFx, final Object controller) {
        if (Objects.isNull(sceneFx)) {
            this.view.getController().handleError("Scene can't be null", true);
        } else {
            final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(sceneFx.getFilePath()));
            loader.setController(controller);
            try {
                this.view.setScene(loader.load());
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
