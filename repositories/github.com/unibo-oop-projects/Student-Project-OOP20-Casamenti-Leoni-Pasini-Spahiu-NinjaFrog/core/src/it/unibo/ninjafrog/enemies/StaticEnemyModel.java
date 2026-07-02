package it.unibo.ninjafrog.enemies;

import it.unibo.ninjafrog.world.Collidable;

/**
 * 
 * Interface that every static enemy has to implement.
 *
 */

public interface StaticEnemyModel extends Collidable {
    /**
     * Method that call other private method for create the body and the head of the
     * static enemy.
     */

    void defineEnemy();

    /**
     * Method that update the static enemy body.
     * 
     * @param dt the delta of the time
     */

    void update(float dt);

    /**
     * 
     * @return the float stateTime of the static enemy
     */

    float getStateTime();

    /**
     * 
     * @return the boolean setToDestroy of the static enemy
     */

    boolean isSetToDestroy();

    /**
     * 
     * @return the boolean destroyed of the static enemy
     */

    boolean isDestroyed();

}
