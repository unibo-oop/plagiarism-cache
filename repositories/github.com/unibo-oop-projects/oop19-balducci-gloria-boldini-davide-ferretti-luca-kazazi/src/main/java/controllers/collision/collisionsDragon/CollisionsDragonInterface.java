package controllers.collision.collisionsDragon;

import model.enemy.Enemy;

public interface CollisionsDragonInterface {

    /**
     * @param e
     * 
     * calls a method to check enemy's collisions
     * 
     */
    void collisionsInGame(Enemy e);

    /**
     * @param e
     * 
     * calls a method to get a random movement
     * 
     */
    void randomMovementsEnemy(Enemy e);

}
