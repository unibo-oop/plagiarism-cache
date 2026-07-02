package model.minigames.oneway;

import java.util.List;
import model.DifficultyLevel;
import model.score.ScoreModel;
import model.score.ScoreModelImpl;
import utility.Pair;

/**
 * Implementation of {@link OneWayModel}.
 * 
 *
 */
public class OneWayModelImpl implements OneWayModel {

    private static final int BASE_POINT = 10;

    private final ScoreModel score;
    private final DifficultyLevel difficulty;
    private final OneWaySettings settings;
    private final PathDrawer pathDrawer;

    /**
     * OneWayModelImpl constructor.
     * 
     * @param selectedDifficulty the difficulty to set
     */
    public OneWayModelImpl(final DifficultyLevel selectedDifficulty) {
        this.difficulty = selectedDifficulty;
        this.settings = new OneWaySettingsImpl(this.difficulty);
        this.score = new ScoreModelImpl(this.difficulty, BASE_POINT);
        this.pathDrawer = new OneWayPathDrawerImpl(this.settings.getGridSize(), this.settings.getArrows());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void oneWayInit() {
        this.pathDrawer.drawPath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hitFinalPosition(final int row, final int col) {
        return this.pathDrawer.getFinalPosition().getX() == row 
                && this.pathDrawer.getFinalPosition().getY() == col ? true : false;
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
    public int getSeconds() {
        return this.settings.getSeconds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DifficultyLevel getDifficulty() {
        return this.difficulty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArrowsCount() {
        return this.settings.getArrows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Direction> getSteps() {
        return this.pathDrawer.getSteps();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getInitialPosition() {
        return this.pathDrawer.getInitialPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getFinalPosition() {
        return this.pathDrawer.getFinalPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getGridSize() {
        return this.settings.getGridSize();
    }

}
