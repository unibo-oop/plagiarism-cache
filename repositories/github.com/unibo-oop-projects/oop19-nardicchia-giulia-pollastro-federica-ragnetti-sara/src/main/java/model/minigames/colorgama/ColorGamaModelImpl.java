package model.minigames.colorgama;

import java.util.Objects;
import java.util.Random;

import model.DifficultyLevel;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;

/**
 * This class implements {@link ColorGamaModel}.
 *
 */
public class ColorGamaModelImpl implements ColorGamaModel {

    private static final String NULL_ARG = " passed is null";

    private final ScoreModel score;
    private final ColorGamaSettings computeValue;
    private final ColorValues colorValues;
    private QuestionType question;

    /**
     * Constructor of {@link ColorGamaModel}.
     * 
     * @param difficulty 
     *          the difficulty selected
     */
    public ColorGamaModelImpl(final DifficultyLevel difficulty) {
        Objects.requireNonNull(difficulty, "DifficultyLevel" + NULL_ARG);
        this.computeValue = new ColorGamaSettingsImpl(difficulty);
        this.score = new ScoreModelImpl(difficulty, this.computeValue.getBasePoint());
        this.colorValues = new ColorValuesImpl(this.computeValue.getGridSize() * this.computeValue.getGridSize(), this.computeValue.getNumColor(), new HSBColorValuesCalculator(difficulty));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoint() {
        this.score.addPoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFinalScore() {
        return this.score.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorGamaSettings getDifficultyValue() {
        return this.computeValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorValuesIterator getColorIterator() {
        return this.colorValues.getIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuestionType getCurrentQuestion() {
        return this.question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuestionType nextQuestion() {
        this.question = QuestionType.values()[new Random().nextInt(QuestionType.values().length)];
        this.colorValues.resetValues(this.question);
        return this.question;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorMethod getColorMethod() {
        return this.colorValues.getColorMethod();
    }
}
