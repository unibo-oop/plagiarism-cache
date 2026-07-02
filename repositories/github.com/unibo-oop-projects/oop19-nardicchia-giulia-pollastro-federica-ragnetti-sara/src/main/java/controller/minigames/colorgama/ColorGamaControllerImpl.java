package controller.minigames.colorgama;

import java.util.Objects;
import java.util.Timer;

import controller.timer.GameTimer;
import model.DifficultyLevel;
import model.minigames.colorgama.ColorGamaModel;
import model.minigames.colorgama.ColorGamaModelImpl;
import model.minigames.colorgama.QuestionType;
import view.controllers.minigames.colorgama.ColorGamaFxImpl;

/**
 * This class implements {@link ColorGamaController}.
 *
 */
public final class ColorGamaControllerImpl implements ColorGamaController {

    private static final String BAD_INDEX = "index must be positive and less than grid size";
    private static final String NULL_ARG = " passed is null";
    private static final long DELAY = 0;
    private static final long PERIOD = 1000;

    private final ColorGamaModel model;
    private final ColorGamaFxImpl view;
    private final GameTimer gameTimer;
    private final Timer timer;

    /**
     * Constructor of {@link ColorGamaController}.
     * 
     * @param view
     *          the view component
     *
     * @param difficulty
     *          the difficulty selected
     */
    public ColorGamaControllerImpl(final ColorGamaFxImpl view, final DifficultyLevel difficulty) {
        Objects.requireNonNull(view, "ColorGamaFxImpl" + NULL_ARG);
        Objects.requireNonNull(difficulty, "DifficultyLevel" + NULL_ARG);
        this.model = new ColorGamaModelImpl(difficulty);
        this.timer = new Timer();
        this.gameTimer = new GameTimer(this.model.getDifficultyValue().getSeconds(), this);
        this.view = view;
    }

    @Override
    public void startTimer() {
        this.timer.schedule(this.gameTimer, DELAY, PERIOD);
        this.view.setInitialSeconds(this.gameTimer.getInitialSeconds());
    }

    @Override
    public void updateTimerView(final int second) {
        this.view.showTimerSeconds(second);
    }

    @Override
    public void timeFinish() {
        this.timer.cancel();
        this.view.showEndGame(this.model.getFinalScore());
    }

    @Override
    public void stopTimer() {
        this.timer.cancel();
        this.gameTimer.cancel();
    }

    @Override
    public void startGame() {
        this.view.initGrid(this.model.getDifficultyValue().getGridSize());
        this.resetGame();
    }

    @Override
    public void resetGame() {
        final QuestionType currentQuestion = this.model.nextQuestion();
        this.view.setColorIterator(this.model.getColorIterator(), this.model.getColorMethod());
        this.view.renderNext(currentQuestion.getQuestionName());
        if (currentQuestion.equals(QuestionType.COLOR)) {
            this.view.showQuestionColor();
        } else {
            this.view.hideQuestionColor();
        }
    }

    @Override
    public void checkAnswer(final int index) {
        final int size = this.model.getDifficultyValue().getGridSize();
        if (index < 0 && index >= size * size) {
            throw new IllegalArgumentException(BAD_INDEX);
        }
        if (this.model.getColorIterator().getRightColorIndex() == index) {
            this.view.showImage(true);
            this.model.addPoint();
        } else {
            this.view.showImage(false);
        }
    }
}
