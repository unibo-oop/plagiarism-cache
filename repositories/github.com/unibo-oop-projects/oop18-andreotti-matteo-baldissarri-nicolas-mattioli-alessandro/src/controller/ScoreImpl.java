package controller;

/**
 * Class that implements Score interface.
 */
public final class ScoreImpl implements Score {

    private static final int FLOORSCORE = 18;
    private int score = 0;
    private int moltiplicator = FLOORSCORE;
    private int oldFloorClimbed = 0;

    @Override
    public void scoreUpdater(final int floorClimbed) {
        if (oldFloorClimbed != floorClimbed) {
            this.score = score + (moltiplicator * (floorClimbed - oldFloorClimbed));
            this.moltiplicator += 2;
            oldFloorClimbed = floorClimbed;
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void increaseScore(final int bonus) {
        this.score += bonus;
    }

    @Override
    public void resetMoltiplicator() {
        this.moltiplicator = FLOORSCORE;
    }
}
