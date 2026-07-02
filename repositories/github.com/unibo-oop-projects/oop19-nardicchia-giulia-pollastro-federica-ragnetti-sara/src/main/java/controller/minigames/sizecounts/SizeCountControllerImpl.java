package controller.minigames.sizecounts;

import java.util.Objects;

import java.util.Timer;

import controller.timer.GameTimer;
import model.DifficultyLevel;
import model.minigames.sizecount.SizeCountModel;
import model.minigames.sizecount.SizeCountModelImpl;
import view.controllers.minigames.sizecount.SizeCountFxImpl;

/**
 * 
 * The implementation of {@link SizeCountController}.
 *
 */
public class SizeCountControllerImpl implements SizeCountController {

    /**
     * The seconds of the {@link GameTimer}.
     */
    private static final int SECONDS = 40;

    private final SizeCountModel model;
    private final SizeCountFxImpl view;
    private final GameTimer gameTimer;
    private final Timer timer;

    /**
     * Constructor for {@link SizeCountController}.
     *
     * @param view 
     *          the view of the game
     * @param difficultyLevel
     *          the {@link DifficultyLevel} of the game
     */
    public SizeCountControllerImpl(final SizeCountFxImpl view, final DifficultyLevel difficultyLevel) {
        this.timer = new Timer();
        this.gameTimer = new GameTimer(SECONDS, this);
        this.view = view;
        this.model = new SizeCountModelImpl(difficultyLevel);
        this.view.setOperations(this.model.getNumOfOperations());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newAttempt(final String answer) {
        Objects.requireNonNull(answer, "answer can't be null");
        if (this.model.isCorrectAnswer(answer)) {
            this.model.addPoint();
            this.view.showCorrectAnswer();
        } else {
            this.view.showWrongAnswer();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        this.model.reset();
        this.view.draw(this.model.getOperations());
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
    public void stopTimer() {
        this.timer.cancel();
        this.gameTimer.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTimer() {
        this.timer.schedule(this.gameTimer, 0, 1000);
        this.view.setInitialSeconds(this.gameTimer.getInitialSeconds());
    }

}
