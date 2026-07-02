package controller.gameloop;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import controller.score.Score;
import controller.score.ScoreUtils;
import javafx.application.Platform;
import model.Model;
import model.entitiesutil.Entity;
import model.entitiesutil.MovementValue;
import view.View;
import view.inputhandler.Action;

/**
 * The game controller. It contains the game loop, which has to talk to Model
 * and View, updating the view according to the model.
 */
public class GameControllerImpl implements GameController {

    private static final int FPS = GameController.FPS;
    private static final int DEL = 1000 / FPS;
    private final Model model;
    private final View view;
    private boolean viewIsVisible;
    private Optional<ScheduledThreadPoolExecutor> timer;
    private boolean isInPause;

    /**
     * Initialize the GameController. It uses the {@link View} and the
     * {@link Model}.
     *
     * @param view  the {@link View} of the game
     * @param model the {@link Model} of the game
     * @throws ParseException
     * @throws IOException
     */
    public GameControllerImpl(final View view, final Model model) {
        this.view = Objects.requireNonNull(view);
        this.view.setController(this);
        this.model = Objects.requireNonNull(model);
        this.model.setController(this);
        this.viewIsVisible = false;
        this.timer = Optional.empty();
        this.isInPause = false;
        this.view.showUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (this.timer.isPresent()) {
            this.timer.get().shutdownNow();
            this.timer = Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startNewGame() {
        if (this.timer.isPresent() && this.isRunning()) {
            this.view.getController().handleError("Can't start a new game: it's already running", true);
        } else {
            this.model.newGame();
            this.timer = Optional.of(this.createTimer());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getLevelEntities() {
        return this.model.getLevel().getEntities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewVisible() {
        return this.viewIsVisible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setViewVisible(final boolean value) {
        this.viewIsVisible = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.model.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        if (!this.timer.isPresent()) {
            return false;
        } else {
            final ScheduledThreadPoolExecutor timer = this.timer.get();
            return !timer.isTerminated() && !timer.isShutdown() && !timer.isTerminating();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleError(final String message, final boolean critical) {
        System.out.println(message);
        this.view.showError(message, critical);
        if (critical) {
            this.view.close();
            this.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newLevelLoaded() {
        Platform.runLater(() -> this.view.getViewManager().getGameView().reset());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.model.getLives();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevelNumber() {
        return this.model.getLevel().getLevelNumber();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovementValue> getExecutedActions() {
        return this.view.getViewManager().getGameView().getExecutedActions().stream()
                .map(this::fromActionToMovementValue).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame() {
        if (!this.isInPause && this.timer.isPresent()) {
            this.stop();
            this.view.getViewManager().openPauseScene();
            this.isInPause = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver() {
        this.writeScore();
        this.stop();
        this.view.getViewManager().openGameOverScene(new Score(this.getPlayerName(), this.model.getScore()));
        this.model.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void victory() {
        this.writeScore();
        this.stop();
        this.view.getViewManager().openVictoryScene(new Score(this.getPlayerName(), this.model.getScore()));
        this.model.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerName(final String playerName) {
        this.model.setPlayerName(playerName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayerName() {
        return this.model.getPlayerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exitPause(final boolean restartTimer) {
        this.isInPause = false;
        if (restartTimer) {
            this.timer = Optional.of(this.createTimer());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPaused() {
        return this.isInPause;
    }

    /*
     * Create the timer for the execution of the game loop.
     */
    private ScheduledThreadPoolExecutor createTimer() {
        final ScheduledThreadPoolExecutor timer = new ScheduledThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors() + 1);
        timer.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        timer.scheduleWithFixedDelay(() -> this.gameLoop(), DEL, DEL, TimeUnit.MILLISECONDS);
        return timer;
    }

    /*
     * Scheduled and executed each 1/FPS seconds with a ScheduledThreadPoolExecutor.
     */
    private void gameLoop() {
        this.model.executeLoopCycle();
        Platform.runLater(() -> this.view.getViewManager().getGameView().update());
    }

    /*
     * Map an Action in a MovementValue.
     */
    private MovementValue fromActionToMovementValue(final Action action) {
        switch (Objects.requireNonNull(action)) {
            case JUMP:
                return MovementValue.CHARACTER_JUMP;
            case RIGHT:
                return MovementValue.CHARACTER_MOVE_RIGHT;
            case LEFT:
                return MovementValue.CHARACTER_MOVE_LEFT;
            case SHOOT:
                return MovementValue.SHOOT;
            case PAUSE:
                this.pauseGame();
            default:

                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Score> getScoreboard() {
        try {
            return ScoreUtils.getScoreManager().getScoreboard();
        } catch (IOException e) {
            this.handleError("Can't open Scoreboard", true);
            return null;
        }
    }

    /*
     * Write the score into the scoreboard.
     */
    private void writeScore() {
        try {
            ScoreUtils.getScoreManager().writeScore(new Score(this.model.getPlayerName(), this.model.getScore()));
        } catch (IOException e) {
            this.handleError("Can't open Scoreboard", true);
        }
    }
}