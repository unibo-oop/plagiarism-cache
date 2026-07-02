package bubbleshooter.model.game.level;

import bubbleshooter.model.game.GameData;

/**
 * Class used to implement the time-based {@link Level}.
 *
 */
public class SurvivalLevel extends AbstractLevel {

    private static final int ONE_SECOND_SCORE = 20;
    private static final int TIME_BEFORE_NEXT_ROW = 15;

    private double timeLeft;

    /**
     * Constructor used to set the type and the time before generating a new row.
     */
    public SurvivalLevel() {
        this.setLevelType(LevelType.SURVIVALMODE);
        this.timeLeft = TIME_BEFORE_NEXT_ROW;
    }

    @Override
    public final void updateScore(final double elapsed) {
        final GameData gameData = this.getGameData();
        gameData.updateScore(gameData.getGameTime() * ONE_SECOND_SCORE);
    }

    @Override
    public final boolean isTimeToNewRow(final double elapsed) {
        this.timeLeft -= elapsed;
        if (this.timeLeft <= 0) {
            this.timeLeft = TIME_BEFORE_NEXT_ROW;
            return true;
        }
        return false;
    }
}
