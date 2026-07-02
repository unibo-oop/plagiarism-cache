package controller;

import controller.player.PlayerPath;
import controller.player.PlayerPathImpl;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleImpl;
import utilities.Utilities;
import view.generic.GameComplete;
import view.generic.GameOver;
import view.generic.LevelComplete;
import view.generic.PauseMenu;

/**
 * Class that manages the game status.
 *
 */
public final class GameStatusImpl implements GameStatus {

    private static final GameStatusImpl SINGLETON = new GameStatusImpl();
    private boolean gamePauseStatus;
    private final PlayerPath playerPath;
    private final Obstacle obstacle;
    private final Score score;
    private final LevelManager levelManager;
    private final SoundManager soundManager;

    /**
     * Constructor.
     */
    private GameStatusImpl() {
        this.playerPath = PlayerPathImpl.get();
        this.obstacle = ObstacleImpl.get();
        this.score = ScoreImpl.get();
        this.levelManager = LevelManagerImpl.get();
        this.soundManager = SoundManagerImpl.get();
    }

    @Override
    public void gameOver() {
        this.playerPath.clearPath();
        this.obstacle.clearObstaclePositions();
        this.score.stopTimer();
        this.score.resetTimer();
        this.levelManager.resetLevelNumber();
        this.soundManager.stopMusic();
        this.soundManager.stopEffect();
        GameOver.get().soundStart();
        GameOver.get().fadeStart();
        SceneManager.get().sceneSetter(GameOver.get().getScene());
    }

    // Clears current level settings, sets level number to the next one and shows
    // GameComplete scene when player completes a level.
    private void levelComplete() {
        this.playerPath.clearPath();
        this.obstacle.clearObstaclePositions();
        LevelComplete.get().setBackgroundImgPath(this.levelManager.chooseLevel().getBackgroundPath());
        this.levelManager.chooseLevel().resetGrid();
        this.score.stopTimer();
        this.levelManager.nextLevel();
        this.soundManager.stopMusic();
        this.soundManager.stopEffect();
        LevelComplete.get().soundStart();
        LevelComplete.get().fadeStart();
        LevelComplete.get().setBackground();
        SceneManager.get().sceneSetter(LevelComplete.get().getScene());
    }

    // Clears current level settings, sets level number to the first one and shows
    // GameComplete scene when player completes the game.
    private void gameComplete() {
        this.playerPath.clearPath();
        this.obstacle.clearObstaclePositions();
        this.score.stopTimer();
        this.score.leaderboard();
        this.score.resetTimer();
        this.levelManager.resetLevelNumber();
        this.soundManager.stopMusic();
        this.soundManager.stopEffect();
        GameComplete.get().soundStart();
        GameComplete.get().fadeStart();
        SceneManager.get().sceneSetter(GameComplete.get().getScene());
    }

    @Override
    public void gamePause() {
        this.score.stopTimer();
        this.setGamePause(true);
        this.soundManager.stopEffect();
        PauseMenu.get().updateVolumeSlider();
        SceneManager.get().sceneSetter(PauseMenu.get().getScene());
    }

    @Override
    public void success() {
        if (this.levelManager.getLevelNumber() == Utilities.TOTAL_LEVELS) {
            this.gameComplete();
        } else {
            this.levelComplete();
        }
    }

    @Override
    public boolean isGamePause() {
        return this.gamePauseStatus;
    }

    @Override
    public void setGamePause(final boolean gamePause) {
        this.gamePauseStatus = gamePause;
    }

    /**
     * Returns an instance of GameStatusImpl.
     * 
     * @return an instance of GameStatusImpl
     */
    public static GameStatusImpl get() {
        return SINGLETON;
    }
}
