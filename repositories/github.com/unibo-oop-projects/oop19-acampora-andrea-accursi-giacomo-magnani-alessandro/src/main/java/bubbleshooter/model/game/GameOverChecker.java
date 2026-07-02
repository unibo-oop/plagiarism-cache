package bubbleshooter.model.game;

import bubbleshooter.model.Model;
import bubbleshooter.model.bubble.BubbleType;
import bubbleshooter.model.game.level.Level;

/**
 * Class that checks if the {@link Level} is in GameOver.
 * Used by {@link AbstractLevel}.
 */
public class GameOverChecker {

    private static final double LIMITS = Model.WORLD_HEIGHT / 1.3;
    private final Level level;

    /**
     * Constructor for a new GameOverChecker.
     * 
     * @param level , the rapresentation of the game.
     */
    public GameOverChecker(final Level level) {
        this.level = level;
    }

    /**
     * Method that checks if the {@link Level} is in GameOver.
     * @return the position of the {@link Bubble}s is greater than the maximum limit
     */
    public final boolean checkGameOver() {
        return this.level.getBubblesManager().getAllBubbles().stream().filter(b -> b.getType()
                .equals(BubbleType.GRID_BUBBLE)).anyMatch(b -> b.getPosition().getY() > LIMITS); 
    }

    /**
     * Method that checks if the {@link Level} is in GameOver.
     * @param bubblePosition , the position of {@link Bubble}
     * @return the position of the {@link Bubble}s is greater than the maximum limit
     */
    public final boolean isGameOver(final double bubblePosition) {
        return bubblePosition > LIMITS;
    }
}
