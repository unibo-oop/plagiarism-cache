package bubbleshooter.controller;

import java.util.Collections;
import java.util.List;
import bubbleshooter.controller.engine.GameLoop;
import bubbleshooter.controller.input.SwitcherController;
import bubbleshooter.controller.sound.SoundGameEngine;
import bubbleshooter.controller.engine.BasicGameLoop;
import bubbleshooter.model.Model;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.game.level.LevelType;
import bubbleshooter.model.highscore.HighscoreStore;
import bubbleshooter.model.highscore.HighscoreStoreImpl;
import bubbleshooter.model.highscore.HighscoreStructure;
import bubbleshooter.model.highscore.ScoreManager;
import bubbleshooter.view.View;

/**
 * Class which implements the {@link Controller} interface.
 * Used to communicate with {@link Model} , {@link View} and to start the {@link GameLoop}.
 */
public class ControllerImpl implements Controller {

    private final Model model;
    private final View view;
    private GameLoop engine;
    private final HighscoreStore highscoreStore;
    private ScoreManager scoresManager;
    private SwitcherController switcherController;

    /**
     * @param model The {@link Model} of the Game.
     * @param view The {@link View} of the Game.
     */
    public ControllerImpl(final Model model, final View view) {
     this.model = model;
     this.view = view;
     this.highscoreStore = new HighscoreStoreImpl();
    }

    /**
    * The method to start the game and the {@link Level}.
    * @param levelType The type of the level.
    */
    @Override
    public final void startGame(final LevelType levelType) {
     this.engine = new SoundGameEngine(new BasicGameLoop(this.view, this.model));
     this.startSelectedGame(levelType);
     this.scoresManager = new ScoreManager(this.model.getLevel().getGameData());
     this.switcherController = new SwitcherController(this.getBubbles());
     this.engine.startLoop();
    }

    private void startSelectedGame(final LevelType levelType) {
        if (levelType.equals(LevelType.BASICMODE)) {
            this.model.startBasicGame();
        } else if (levelType.equals(LevelType.SURVIVALMODE)) {
            this.model.startSurvivalGame();
        }
    }

    /**
     * @return The List of the current Bubble in the Game.
     */
    @Override
    public final List<Bubble> getBubbles() {
        return Collections.unmodifiableList(this.model.getBubbles());
    }


    /**
     * Method used to save the scores in the highscores.
     * 
     * @param username the name of the player.
     */
    @Override
    public final void saveScore(final String username) {
        this.highscoreStore.addScore(new HighscoreStructure(username.replace(" ", "_"), 
                this.getScoreManager().getScore(), this.getCurrentLevel()));
    }

    /**
     * Method called by {@link GameOverController} and {@link HighscoreController}
     * for have informations about the scores.
     * 
     * @return the ScoreManager.
     */
    @Override
    public final ScoreManager getScoreManager() {
        return this.scoresManager;
    }

    /**
     * @return the HighscoreStore.
     */
    @Override
    public final HighscoreStore getHighscoreStore() {
        return this.highscoreStore;
    }

    /**
     * Method used to have the current game modality.
     * 
     * @return the current game modality.
     */
    @Override
    public final LevelType getCurrentLevel() {
        return this.model.getLevel().getLevelType();
    }

    /**
     * @return The Engine of the Game.
     */
    @Override
    public final GameLoop getGameEngine() {
        return this.engine;
    }

    /**Method used to get the controller of switch function.
     * @return the Controller of Switch.
     */
    @Override
    public SwitcherController getSwitcherController() {
        return this.switcherController;
    }

    /**
     * @return the width of the game.
     */
    @Override
    public final double getWorldWidth() {
        return Model.WORLD_WIDTH;
    }

    /**
     * @return the height of the game.
     */
    @Override
    public final double getWorldHeight() {
        return Model.WORLD_HEIGHT;
    }
}
