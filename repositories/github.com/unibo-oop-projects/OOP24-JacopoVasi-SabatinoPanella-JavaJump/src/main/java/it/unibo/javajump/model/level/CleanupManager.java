package it.unibo.javajump.model.level;

import it.unibo.javajump.model.GameModel;

/**
 * The interface that describes the Cleanup manager, that should remove useless objects from the game.
 */
public interface CleanupManager {
    /**
     * Cleanup objects method, it reads the model to get the GameObjects and remove them.
     *
     * @param model the GameModel
     */
    void cleanupObjects(GameModel model);
}
