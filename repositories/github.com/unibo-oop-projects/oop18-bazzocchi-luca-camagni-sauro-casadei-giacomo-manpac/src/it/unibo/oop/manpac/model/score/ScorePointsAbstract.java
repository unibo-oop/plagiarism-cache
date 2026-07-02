package it.unibo.oop.manpac.model.score;

import java.util.Objects;

/**
 * Represent the actual and record score of player. It is necessary to implement
 * the abstract methods that define mathematical and logical operations between
 * the Points.
 * 
 * @param <X> Type of points. The use of an immutable type (eg Integer, String,
 *            etc.) is highly recommended.<br>
 *            The type MUST extend java.io.Serializable.
 */
public abstract class ScorePointsAbstract<X extends java.io.Serializable> implements ScorePoints<X> {

    // serial version of ScorePointsAbstract
    private static final long serialVersionUID = -1768677047139009423L;

    // field of highScore and currentScore
    private final Points<X> highScore;
    private final Points<X> currentScore;

    /**
     * Constructor of ScorePointsAbstract with high score and current score to
     * default value (returned by the abstract methods {@link #highScoreDefault()}
     * and {@link #currentScoreDefault()}).
     */
    public ScorePointsAbstract() {
        this.highScore = new PointsImpl.Mutable<>(highScoreDefault().getValue());
        this.currentScore = new PointsImpl.Mutable<>(currentScoreDefault().getValue());
    }

    /**
     * Constructor of ScorePointsAbstract with loading of high score by parameter,
     * instead the current score is set to the default value (returned by the
     * abstract method {@link #currentScoreDefault()}).
     * 
     * @param highScore score of the highest score to load (of type {@link Points});
     *                  if the parameter is negative, the default value will be used
     *                  (returned by the abstract method
     *                  {@link #highScoreDefault()})
     */
    public ScorePointsAbstract(final Points<X> highScore) {
        this();
        if (isPointsNotNull(highScore)) {
            setScore(this.highScore, retPositivePoints(highScore, highScoreDefault()));
        }
    }

    /**
     * {@inheritDoc}<br>
     * WARNING: the copy of Points returned does not have a copy of the value, but
     * the value itself! Be careful if the value is not "immutable".
     */
    @Override
    public Points<X> getHighScore() {
        return ((PointsImpl<X>) this.highScore).getCopy();
    }

    /**
     * {@inheritDoc}<br>
     * WARNING: the copy of Points returned does not have a copy of the value, but
     * the value itself! Be careful if the value is not "immutable".
     */
    @Override
    public Points<X> getCurrentScore() {
        return ((PointsImpl<X>) this.currentScore).getCopy();
    }

    /**
     * Set the high score to value of parameter (for example for loading the high
     * score). If high score is less than current score, highScore takes the points
     * of current score. If this is not wanted, please first reset the current
     * score.
     * 
     * @param highScore score of the highest score to set (of type {@link Points});
     *                  if the parameter is negative, the default value will be used
     *                  (returned by the abstract method
     *                  {@link #highScoreDefault()})
     */
    @Override
    public void setHighScore(final Points<X> highScore) {
        if (isPointsNotNull(highScore)) {
            setScore(this.highScore, retPositivePoints(highScore, highScoreDefault()));
            if (currentScoreGreaterHighScore()) {
                newHighScore();
            }
        }
    }

    /**
     * {@inheritDoc} The minimum value of current score will be defined by
     * {@link #currentScoreDefault()}. If current score exceeds high score, then
     * current score will become the new high score
     * 
     * @param points {@link Points} to add in current score (the points can be
     *               negative)
     */
    @Override
    public void increaseCurrentScore(final Points<X> points) {
        if (isPointsNotNull(points)) {
            setScore(this.currentScore, retPositivePoints(sum(this.currentScore, points), currentScoreDefault()));
            if (currentScoreGreaterHighScore()) {
                newHighScore();
            }
        }
    }

    /**
     * Reset the current score to default value (returned by the abstract methods
     * {@link #currentScoreDefault()}).
     */
    @Override
    public void resetCurrentScore() {
        setScore(this.currentScore, currentScoreDefault());
    }

    /**
     * Reset the high score to default value (returned by the abstract methods
     * {@link #highScoreDefault()} and {@link #currentScoreDefault()}).
     */
    @Override
    public void resetScorePoints() {
        resetHighScore();
        resetCurrentScore();
    }

    /**
     * Set the current score to high score.
     */
    @Override
    public void newHighScore() {
        setScore(this.highScore, this.currentScore);
    }

    // ABSTRACT METHOD

    /**
     * {@inheritDoc}
     */
    public abstract ScorePoints<X> getCopy();

    /**
     * Return the default value of high score; returning the null value will cause
     * the class to malfunction (by throwing {@link NullPointerException}).
     * 
     * @return default value of high score
     */
    protected abstract Points<X> highScoreDefault();

    /**
     * Return the default value of current score; returning the null value will
     * cause the class to malfunction (by throwing {@link NullPointerException}).
     * 
     * @return default value of current score
     */
    protected abstract Points<X> currentScoreDefault();

    /**
     * Define the sum operation of two {@link Points}; returning the null value will
     * cause the class to malfunction (by throwing {@link NullPointerException}).
     * 
     * @param points1 First addends {@link Points}
     * @param points2 Second addends {@link Points}
     * @return the sum between point1 and point2
     */
    protected abstract Points<X> sum(Points<X> points1, Points<X> points2);

    /**
     * Define the greater-than operation of two {@link Points}.
     * 
     * @param points1 First {@link Points}
     * @param points2 Second {@link Points}
     * @return true if points1 &#62; points2.
     */
    protected abstract boolean greaterThan(Points<X> points1, Points<X> points2);

    /**
     * Define the greater-than or equals operation between {@link Points} and ZERO.
     * 
     * @param points {@link Points} to compare
     * @return true if points &#62;= ZERO 
     */
    protected abstract boolean greaterThanOrEqualsZero(Points<X> points);

    // UTILITY PRIVATE METHOD

    /**
     * Reset the current score to default value (returned by the abstract methods
     * {@link #highScoreDefault()}).
     */
    private void resetHighScore() {
        setScore(this.highScore, highScoreDefault());
    }

    // private setter for set any score
    private void setScore(final Points<X> score, final Points<X> points) {
        ((PointsImpl.Mutable<X>) score).setPoints(points.getCopy().getValue());
    }

    // Checker for any negative points value; if points is positive (points >= ZERO)
    // return points, else return defaultPoints
    private Points<X> retPositivePoints(final Points<X> points, final Points<X> defaultPoints) {
        return greaterThanOrEqualsZero(points) ? points : defaultPoints;
    }

    // Checker that checks if the currentScore is greater than the highScore
    private boolean currentScoreGreaterHighScore() {
        return greaterThan(this.currentScore, this.highScore);
    }

    // checker for null points
    private boolean isPointsNotNull(final Points<X> points) {
        return Objects.nonNull(points);
    }

    /**
     * To get the hash code.
     * 
     * @return a hash code value of ScorePointsAbstract using hashCode() of
     *         {@link PointsImpl}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * (prime + ((PointsImpl<X>) this.currentScore).hashCode())
                + ((PointsImpl<X>) this.highScore).hashCode();

    }

    /**
     * Indicates whether some other {@link ScorePointsAbstract} is "equal to" this
     * one.
     * 
     * @param obj the reference {@link ScorePointsAbstract} with which to compare
     * @return true if this {@link ScorePointsAbstract} is the same scores, false
     *         otherwise
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        return (Objects.nonNull(obj) && this.getClass() == obj.getClass() // check on obj - not null and same class
                && (this.highScore.equals(((ScorePoints<X>) obj).getHighScore()) // check the fields of this with those of obj
                        && (this.currentScore.equals(((ScorePoints<X>) obj).getCurrentScore()))));
    }

    /**
     * Returns a string representation of the {@link ScorePointsAbstract}.
     */
    @Override
    public String toString() {
        return new StringBuilder().append("ScorePoints [highScore = ").append(this.highScore.getValue())
                                  .append(" , currentScore = ").append(this.currentScore.getValue())
                                  .append(" ] ").toString();
    }
}
