package javawulf.model.player;

/**
 * Class that implements the Score interface.
 */
public final class ScoreImpl implements Score {

    private int points;
    private Multiplier multiplier;

    /**
     * Creates the score for the current game.
     * If a certain amount has been specified it will be used as a strating point
     * for the point total (could be used as a bonus for choosing a harder difficulty)
     * 
     * @param points The starting amount of points for the current game
     */
    public ScoreImpl(final int points) {
        this.points = points;
        this.multiplier = Multiplier.DEFAULT;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getMultiplier() {
        return this.multiplier.getValue();
    }

    @Override
    public void setMultiplier(final Multiplier multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void addPoints(final int points) {
        this.points = this.points + points * this.getMultiplier();
    }

}
