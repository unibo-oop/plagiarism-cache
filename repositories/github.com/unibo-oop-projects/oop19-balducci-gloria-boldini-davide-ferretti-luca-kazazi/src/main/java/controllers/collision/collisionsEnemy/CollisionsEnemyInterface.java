package controllers.collision.collisionsEnemy;

import model.enemy.Enemy;
import model.gameObject.GameObject;

public interface CollisionsEnemyInterface {

    /**
     * @param obj1
     * @param obj2
     * 
     *             calls a method to manage enemy's collisions
     */
    void collisionsInGame(Enemy obj1, GameObject obj2);

}
