package it.unibo.ninjafrog.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Interface with the essential methods for view part of the enemy.
 */
public interface EnemyView {
    /**
     * Method that update the view part of the enemy.
     * 
     * @param body the body of the enemy
     * @param dt   the delta of the time
     */

    void update(Body body, float dt);

    /**
     * Method that draw the view part of the enemy.
     * 
     * @param batch the game batch
     */

    void draw(SpriteBatch batch);

    /**
     * 
     * @return true if the current state of the enemy allows to be killed.
     */

    boolean isKillable();

    /**
     * Method that set the texture of the enemy with the death animation.
     */

    void setDeathRegion();

    /**
     * 
     * @return the X coordinate of the enemy
     */

    float getX();

    /**
     * 
     * @return the Y coordinate of the enemy
     */

    float getY();

}
