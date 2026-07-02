package bubbleshooter.model.game.level;

import bubbleshooter.model.game.GameData;

/**
 * Class that represent the basic level of the game.
 *
 */
public class BasicLevel extends AbstractLevel {

    private static final int BUBBLE_SCORE = 20;
    private static final int WRONG_SHOOTS_BEFORE_NEW_ROW = 5;

    /**
     * Constructor used to set the level type.
     */
    public BasicLevel() {
        this.setLevelType(LevelType.BASICMODE);
    }

    @Override
    public final void updateScore(final double elapsed) {
        final GameData gameData = this.getGameData();
        gameData.updateScore(gameData.getDestroyedBubbles() * BUBBLE_SCORE);
    }

    @Override
    public final boolean isTimeToNewRow(final double elapsed) {
        final GameData gameData = this.getGameData();
        if (gameData.getWrongShoots() == WRONG_SHOOTS_BEFORE_NEW_ROW) {
            gameData.clearWrongShoots();
            return true;
        }
        return false;
    }
}
