package it.unibo.oop.manpac.model.score;

/**
 * Represent the actual and record score of player.
 */
public final class ScorePointsInteger extends ScorePointsAbstract<Integer> {

    // serial version of ScorePointsInteger
    private static final long serialVersionUID = -5024742031709470049L;

    // constants for represent the default value/Points of high and current score
    private static final int HIGHSCORE_VALUE_DEFAULT = 0;
    private static final int CURRENTSCORE_VALUE_DEFAULT = 0;

    private static final Points<Integer> HIGHSCORE_POINTS_DEFAULT = new PointsImpl<>(HIGHSCORE_VALUE_DEFAULT);
    private static final Points<Integer> CURRENTSCORE_POINTS_DEFAULT = new PointsImpl<>(CURRENTSCORE_VALUE_DEFAULT);

    /**
     * Constructor of ScorePointsInteger with high score and current score to
     * default value ({@value #HIGHSCORE_VALUE_DEFAULT} for high score and
     * {@value #CURRENTSCORE_VALUE_DEFAULT} for current score).
     */
    public ScorePointsInteger() {
        super();
    }

    /**
     * Constructor of ScorePointsInteger with loading of high score by parameter,
     * instead the current score is set to the default value
     * ({@value #CURRENTSCORE_VALUE_DEFAULT}).
     * 
     * @param highScore score of the highest score to load (of type {@link Points});
     *                  if the parameter is negative, the default value will be used
     *                  ({@value #HIGHSCORE_VALUE_DEFAULT})
     */
    public ScorePointsInteger(final Points<Integer> highScore) {
        super(highScore);
    }

    @Override
    public ScorePoints<Integer> getCopy() {
        final ScorePoints<Integer> sp = new ScorePointsInteger();
        sp.setHighScore(getHighScore().getCopy());
        sp.increaseCurrentScore(getCurrentScore().getCopy());
        return sp;
    }

    @Override
    protected Points<Integer> highScoreDefault() {
        return HIGHSCORE_POINTS_DEFAULT;
    }

    @Override
    protected Points<Integer> currentScoreDefault() {
        return CURRENTSCORE_POINTS_DEFAULT;
    }

    @Override
    protected Points<Integer> sum(final Points<Integer> points1, final Points<Integer> points2) {
        Points<Integer> pointsTemp = new PointsImpl<>(points1.getValue() + points2.getValue());

        if (!greaterThanOrEqualsZero(pointsTemp) && greaterThanOrEqualsZero(points1) && greaterThanOrEqualsZero(points2)) {
            pointsTemp = new PointsImpl<>(Integer.MAX_VALUE);
        }
        return pointsTemp;
    }

    @Override
    protected boolean greaterThan(final Points<Integer> points1, final Points<Integer> points2) {
        return points1.getValue() > points2.getValue();
    }

    @Override
    protected boolean greaterThanOrEqualsZero(final Points<Integer> points) {
        return points.getValue() >= 0;
    }

}
