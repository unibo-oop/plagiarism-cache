package controllers.collision.collisionGeneral;

import model.enemy.Enemy;
import model.gameObject.GameObject;

public interface CollisionsInterface {

    /**
     * 
     * @param obj1
     * @param obj2
     * @return true if there's a collision, false otherwise
     * 
     * general method to check collisions
     */
    boolean checkCollisions(GameObject obj1, GameObject obj2);

    /**
     * 
     * @param obj1
     * @param obj2
     * 
     *             method to check collision between a gameObject and a wall
     */
    void collisionsWall(GameObject obj1, GameObject obj2);

    /**
     * @param obj1
     * @param obj2
     * @return true if collision has been detected
     * 
     *         method to check collisions between enemy rectangle and general
     *         gameObject
     */
    boolean checkCollisionsEnemyRectangle(GameObject obj1, Enemy obj2);

}
