package it.unibo.ninjafrog.enemies;

import it.unibo.ninjafrog.world.Collidable;

/**
 * Interface with the method that every dynamic enemy have to implement.
 *
 */

public interface DynamicEnemyModel extends Collidable {
    /**
     * Method that call other private method for create the body and the head of the
     * dynamic enemy.
     */

    void defineEnemy();

    /**
     * Method that update the class enemyModelImpl of the dynamic enemy.
     * 
     * @param dt the delta of the time
     */

    void update(float dt);

    /**
     * Method that change the direction of the dynamic enemy.
     * 
     * @param x true if you want to change the X direction
     * @param y true if you want to change the Y direction
     */

    void reverseVelocity(boolean x, boolean y);

    /**
     * 
     * @return the boolean is setToDestroy of the dynamic enemy
     */

    boolean isSetToDestroy();

    /**
     * 
     * @return the boolean destroyed of the dynamic enemy
     */

    boolean isDestroyed();

    /**
     * 
     * @return the float stateTime of the dynamic enemy
     */

    float getStateTime();

    /**
     * 
     * @return the boolean runningLeft of the dynamic enemy
     */

    boolean isRunningLeft();

    /**
     * Method that set the boolean runningLeft of the dynamic enemy.
     * 
     * @param b how u want to set the boolean runningLeft
     */

    void setRunningLeft(boolean b);

}
