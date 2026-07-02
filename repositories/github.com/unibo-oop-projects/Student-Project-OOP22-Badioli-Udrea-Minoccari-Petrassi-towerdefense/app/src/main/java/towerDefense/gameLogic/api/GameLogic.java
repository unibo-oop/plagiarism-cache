package towerDefense.gameLogic.api;

import towerDefense.entities.api.Entity;
import towerDefense.entities.api.MovingEntity;

public interface GameLogic {

    /**
     * Checks if an entity is colliding with another
     * @param a
     *          the first entity to check
     * @param b
     *          the second entity to check
     */
    public <X extends MovingEntity> boolean checkCollision(X a, Entity b);

    /**
     * Updates the state of the game
     */
    public void update();

    /**
     * Adds to the entities list the type of unit requested and then subtracts its cost to the total money
     * @param cost
     *          the cost of the summoned unit
     * @param type
     *          the type of the summoned unit
     */
    public void summonEntity(final int cost, final int type);

    /**
     * Summons a unit with zero cost
     * @param type
     *          the type of the summoned unit
     */
    public void summonfreeEntity(final int type);

    /**
     * Summons an enemy with a random chance of being different ones
     */
    public void summonEnemy();

}
