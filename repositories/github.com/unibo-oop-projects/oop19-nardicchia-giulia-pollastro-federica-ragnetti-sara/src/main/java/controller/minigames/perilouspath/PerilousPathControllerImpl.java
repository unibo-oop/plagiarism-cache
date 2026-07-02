package controller.minigames.perilouspath;

import java.util.Objects;
import java.util.Timer;

import controller.timer.GameTimer;
import model.DifficultyLevel;
import model.minigames.perilouspath.PerilousPathModel;
import model.minigames.perilouspath.PerilousPathModelImpl;
import view.controllers.minigames.perilouspath.PerilousPathFxImpl;

/**
 * Implementation of {@link PerilousPathController}.
 */
public class PerilousPathControllerImpl implements PerilousPathController {

    /**
     * The game's duration.
     */
    private static final int SECONDS = 30;

    /**
     * The duration dedicated to the memorization of the mines position.
     */
    private static final int MILLIS_MEMORIZATION_MINES = 3000;

    /**
     * The duration dedicated to the visualization of the mine's explosion.
     */
    private static final int MILLIS_VISUALIZATION_EXPLOSION = 1000;

    /**
     * The delay of the timer.
     */
    private static final long DELAY = 0;

    /**
     * The period of the timer.
     */
    private static final long PERIOD = 1000;

    private final PerilousPathModel model;
    private final PerilousPathFxImpl view;
    private final Timer timer;
    private final GameTimer gameTimer;
    private boolean timeFinish;

    /**
     * Constructor of {@link PerilousPathController}.
     * 
     * @param difficultyLevel 
     *                       the current difficulty level
     * @param perilousPathFx 
     *                       the view controller
     */
    public PerilousPathControllerImpl(final DifficultyLevel difficultyLevel, final PerilousPathFxImpl perilousPathFx) {
        Objects.requireNonNull(difficultyLevel);
        Objects.requireNonNull(perilousPathFx);
        this.model = new PerilousPathModelImpl(difficultyLevel);
        this.view = perilousPathFx;
        this.timer = new Timer();
        this.gameTimer = new GameTimer(SECONDS, this);
        this.timeFinish = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        this.gameTimer.restart();
        this.view.showStart(this.model.getStart());
        this.view.showFinish(this.model.getFinish());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        if (!this.timeFinish) {
            this.gameTimer.pause();
            this.model.resetGame();
            this.view.showMines(this.model.getMines());
            this.view.animation(MILLIS_MEMORIZATION_MINES, false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.view.draw(this.model.getSize());
        this.resetGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitAttempt(final int row, final int col) {
        if (this.model.hit(row, col)) {
            this.view.showStep(row, col);
        }
        if (this.model.isDone()) {
            this.model.addPoint();
            this.view.restore();
            this.resetGame();
        }
        if (this.model.isFailed()) {
            this.view.showMines(this.model.getMines());
            this.view.showExplosion(row, col);
            this.view.animation(MILLIS_VISUALIZATION_EXPLOSION, true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimerView(final int second) {
        if (second == 0) {
            this.timeFinish = true;
        }
        this.view.showTimerSeconds(second);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTimer() {
        this.timer.schedule(this.gameTimer, DELAY, PERIOD);
        this.view.setInitialSeconds(this.gameTimer.getInitialSeconds());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopTimer() {
        this.timer.cancel();
        this.gameTimer.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timeFinish() {
        this.timer.cancel();
        this.view.showEndGame(this.model.getFinalScore());
    }
}
