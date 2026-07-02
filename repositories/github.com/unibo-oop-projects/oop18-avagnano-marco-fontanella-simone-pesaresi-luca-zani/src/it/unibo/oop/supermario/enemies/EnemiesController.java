package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * This class controls everything about enemies.
 */
public interface EnemiesController {
    /** Updates the play screen of enemies.
     * 
     * @param dt the delta time in update process
     */
    void update(float dt);

    /** Draws the play screen of enemies. 
     * @param batch drawing commands
     */
    void draw(Batch batch);
}
