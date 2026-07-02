package model.minigames.truecolor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import model.DifficultyLevel;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;
import utility.Pair;

/**
 * Implementation of {@link TrueColorModel}.
 */
public class TrueColorModelImpl implements TrueColorModel {

    /**
     * TrueColor's base point.
     */
    private static final int BASE_POINT = 4;

    /**
     * TrueColor minigame's duration.
     */
    private static final int SECONDS = 30;
    private static final String POS_ANSWER = "YES";
    private final ScoreModel score;
    private final DifficultyStrategy difficultySpecific;
    private Map<StatusColor, List<Pair<Colors, Colors>>> randomColorMap = new HashMap<>();
    private Boolean bool = false;

    /**
     * Constructor of {@link TrueColorModel}.
     * 
     * @param difficultyLevel    the difficulty selected
     * 
     * @param difficultyStrategy the difficulty create
     * 
     */
    public TrueColorModelImpl(final DifficultyLevel difficultyLevel, final DifficultyStrategy difficultyStrategy) {
        Objects.requireNonNull(difficultyLevel, "difficulty is null");
        Objects.requireNonNull(difficultyStrategy, "DifficultyStrategy is null");
        this.score = new ScoreModelImpl(difficultyLevel, BASE_POINT);
        this.difficultySpecific = difficultyStrategy;
        this.randomColorMap = this.difficultySpecific.getRandomColorMap();
    }

    private void alternateMethod() {
        final Random random = new Random();
        this.bool = random.nextBoolean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadRandomColorMap() {
        this.alternateMethod();
        this.randomColorMap.clear();
        this.difficultySpecific.initMap();
        this.randomColorMap = this.difficultySpecific.getRandomColorMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchingMeaningAndInk(final String selectedAnswer) {
        Objects.requireNonNull(selectedAnswer);
        final boolean match = this.randomColorMap.get(StatusColor.MEANCOLOR).stream()
                .anyMatch(mean -> this.randomColorMap.get(StatusColor.TRUECOLOR).stream().anyMatch(trueC -> mean.getX().equals(trueC.getY())));
        final boolean answer = selectedAnswer.contentEquals(POS_ANSWER);
        return match && answer || !match && !answer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<StatusColor, List<Pair<Colors, Colors>>> getRandomColorMap() {
        return this.randomColorMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getStatusMethod() {
        return this.bool;
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
    public int getSecondsGame() {
        return SECONDS;
    }
}
