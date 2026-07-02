package controller.minigames.oneway;

import java.util.Timer;

import controller.timer.GameTimer;
import model.DifficultyLevel;
import model.minigames.oneway.OneWayModel;
import model.minigames.oneway.OneWayModelImpl;
import view.controllers.minigames.oneway.OneWayFxImpl;

/**
 * Implementation of {@link OneWayController}.
 *
 */
public class OneWayControllerImpl implements OneWayController {

    private final OneWayFxImpl view;
    private final OneWayModel model;

    private final GameTimer gameTimer;
    private final Timer timer;

    /**
     * SimpleOneWayController constructor.
     * @param oneWayFx
     *              the {@link OneWayFx} to set
     * @param difficultyLevel
     *              the {@link DifficultyLevel}
     */
    public OneWayControllerImpl(final OneWayFxImpl oneWayFx, final DifficultyLevel difficultyLevel) {
        this.view = oneWayFx;
        this.model = new OneWayModelImpl(difficultyLevel);
        this.timer = new Timer();
        this.gameTimer = new GameTimer(this.model.getSeconds(), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit(final int row, final int col) {
        if (this.model.hitFinalPosition(row, col)) {
            this.model.addPoint();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timeExpired() {
        this.view.showEndGame(this.model.getFinalScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        this.model.oneWayInit();
        this.view.updateArrows(this.model.getSteps());
        this.view.updateView(this.model.getInitialPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showSolution() {
        this.view.showCorrectAnswer(this.model.getFinalPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void firstInit() {
        this.view.drawGrid(this.model.getGridSize());
        this.model.oneWayInit();
        this.view.drawArrows(this.model.getSteps());
        this.view.updateView(this.model.getInitialPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimerView(final int second) {
        this.view.showTimerSeconds(second);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timeFinish() {
        this.timer.cancel();
        this.view.showEndGame(this.model.getFinalScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTimer() {
        this.timer.schedule(this.gameTimer, 0, 1000);
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
}
