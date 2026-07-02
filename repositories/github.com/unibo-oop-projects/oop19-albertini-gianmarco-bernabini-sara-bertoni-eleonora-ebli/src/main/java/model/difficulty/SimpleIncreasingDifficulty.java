package model.difficulty;

/**
 * A class to increase the difficulty of the game constantly. Temporary maybe
 * useful for testing. In the final game i need to increase the difficulty based
 * on the score.
 */
public class SimpleIncreasingDifficulty extends AbstractDifficultyImpl {

    private boolean shortTurn = true;
    private final SpeedOffsetIncrement increment;
    private final int limit;

    public SimpleIncreasingDifficulty(final int limit, final SpeedOffsetIncrement increment) {
        super();
        this.limit = limit;
        this.increment = increment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increase() {
        this.increaseOffset(increment.getIncrement());

        if (!isLimitReached()) {
            if (shortTurn) {
                this.incrementShort();

            } else {
                this.incrementLong();
            }

            this.shortTurn = !this.shortTurn;
        }

    }

    private boolean isLimitReached() {
        return this.getNLong() + this.getNShort() >= limit;
    }


}
