package model.difficulty;

/**
 * 
 * Abstract class that models the increasing difficulty of the game. It
 * increases its parameters but it stays generic in how to do it.
 *
 */
public abstract class AbstractDifficultyImpl implements Difficulty {
    /* valori da aggiustare in seguito ai test */
    /* aumento il range delle velocità */
    private static final double BASE_SPEED = 0.05;
    private static final int DEFAULT_SHORT_WORDS = 2;
    private static final int DEFAULT_LONG_WORDS = 1;

    private double speedOffset;
    private int nShort;
    private int nLong;

    public AbstractDifficultyImpl() {
        this.speedOffset = 0;
        this.nShort = DEFAULT_SHORT_WORDS;
        this.nLong = DEFAULT_LONG_WORDS;
    }

    /**
     * {@inheritDoc}
     */
    public double getMinSpeed() {
        return BASE_SPEED;
    }

    /**
     * {@inheritDoc}
     */
    public double getMaxSpeed() {
        return BASE_SPEED + speedOffset;
    }

    /**
     * {@inheritDoc}
     */
    public int getNShort() {
        return this.nShort;
    }

    /**
     * {@inheritDoc}
     */
    public int getNLong() {
        return this.nLong;
    }

    /**
     * method to bring offset to its initial value.
     */
    protected void resetOffset() {
        this.speedOffset = 0;
    }

    /**
     * method to bring number of short words to its initial value.
     */
    protected void resetnShort() {
        this.nShort = DEFAULT_SHORT_WORDS;
    }

    /**
     * method to bring number of long words to its initial value.
     * 
     */
    protected void resetnLong() {
        this.nLong = DEFAULT_LONG_WORDS;
    }

    /**
     * Method to increase the range of value for the speed.
     * 
     * @param n
     *              the increase
     */
    protected void increaseOffset(final double n) {
        this.speedOffset = this.speedOffset + n;
    }

    /**
     * Method to increase the number of short words by 1.
     */
    protected void incrementShort() {
        this.nShort = this.nShort + 1;
    }

    /**
     * Method to increase the number of long words by 1.
     */
    protected void incrementLong() {
        this.nLong = this.nLong + 1;
    }

}
