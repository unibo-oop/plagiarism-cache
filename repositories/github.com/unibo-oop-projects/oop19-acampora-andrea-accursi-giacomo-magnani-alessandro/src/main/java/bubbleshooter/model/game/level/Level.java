package bubbleshooter.model.game.level;

import bubbleshooter.model.bubble.BubbleFactory;
import bubbleshooter.model.bubble.BubblesManager;
import bubbleshooter.model.bubble.grid.BubbleGridHelper;
import bubbleshooter.model.bubble.grid.BubbleGridManager;
import bubbleshooter.model.collision.CollisionController;
import bubbleshooter.model.game.GameData;
import bubbleshooter.model.game.GameStatus;

/**
 * Represents the game. It's responsible to initialize all {@link Bubble}s and
 * create new row of bubbles.
 */

public interface Level {

    /**
     * Number of bubble per row in the game.
     */
    int NUM_BUBBLES_PER_ROW = 19;

    /**
     * Number of row of bubbles in the game.
     */
    int NUM_ROWS = 8;

    /**
     * Starts selected game and initialize all bubbles.
     */
    void start();

    /**
     * Updates the game.
     * 
     * @param elapsed The time elapsed every {@link GameLoop} cycle.
     */
    void update(double elapsed);

    /**
     * Gets the {@link BubblesManager}.
     * 
     * @return {@link BubblesManager}.
     */
    BubblesManager getBubblesManager();

    /**
     * Loads the {@link ShootingBubble}.
     */
    void loadShootingBubble();

    /**
     * Loads the {@link SwitchBubble}.
     */
    void loadSwitchBubble();

    /**
     * Sets current {@link LevelType}.
     * 
     * @param levelType The type of the level.
     */
    void setLevelType(LevelType levelType);

    /**
     * Sets the {@link GameStatus}.
     * 
     * @param status The status of the game.
     */
    void setGameStatus(GameStatus status);

    /**
     * Gets the {@link GameStatus}.
     * 
     * @return the {@link GameStatus}.
     */
    GameStatus getGameStatus();

    /**
     * Gets the {@link BubblesManager}.
     * 
     * @return {@link BubblesManager}
     */
    BubbleGridManager getGridManager();

    /**
     * Gets the {@link BubbleGridHelper}.
     * 
     * @return the {@link BubbleGridHelper}
     */
    BubbleGridHelper getGridHelper();

    /**
     * Gets the {@link CollisionController}.
     * 
     * @return the {@link CollisionController}.
     */
    CollisionController getCollisionController();

    /**
     * Gets the {@link GameData}.
     * 
     * @return the {@link GameData}.
     */
    GameData getGameData();

    /**
     * Gets the current {@link LevelType}.
     * 
     * @return the {@link LevelType}.
     */
    LevelType getLevelType();

    /**
     * Gets the {@link BubbleFactory}.
     * 
     * @return the {@link BubbleFactory}.
     */
    BubbleFactory getBubbleFactory();

}
