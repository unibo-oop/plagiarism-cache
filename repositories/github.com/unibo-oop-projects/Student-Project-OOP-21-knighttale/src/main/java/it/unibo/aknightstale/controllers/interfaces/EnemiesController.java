package it.unibo.aknightstale.controllers.interfaces;

import it.unibo.aknightstale.controllers.entity.CharacterController;
import it.unibo.aknightstale.utils.CollisionManager;
import it.unibo.aknightstale.utils.Point2D;

public interface EnemiesController {

    /**
     * Draw enemies in the game world.
     */
    void drawEnemies(CollisionManager collision, CharacterController player);

    /**
     * Remove dead enemies from game world.
     */
    void removeDeadEnemies();

    /**
     * Update enemies's direction.
     *
     * @param playerPosition the player position
     */
    void updateDirection(Point2D playerPosition);

    /**
     * Adapt position to screen size.
     *
     * @param traslX the trasl x
     * @param traslY the trasl y
     */
    void adaptPositionToScreenSize(double traslX, double traslY);

    /**
     * Gets the number of enemies alive.
     *
     * @return the num enemy
     */
    int getNumEnemy();

    /**
     * create some enemies.
     * @param numEnemies the number of enemies to create
     */
    void createEnemies(int numEnemies);

}
