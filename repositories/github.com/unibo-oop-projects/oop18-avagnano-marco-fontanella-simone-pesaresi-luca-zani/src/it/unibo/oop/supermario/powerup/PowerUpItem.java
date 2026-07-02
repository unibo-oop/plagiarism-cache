package it.unibo.oop.supermario.powerup;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * PowerUpItem interface.
 */
public interface PowerUpItem {
    /**
     * If power up is to destroy.
     * 
     * @return true if power up is to destroy
     */
    boolean isToDestroy();

    /**
     * If power up is destroyed.
     * 
     * @return true if power up is destroyed
     */
    boolean isDestroyed();

    /**
     * Add power up into destruction queue.
     */
    void queueDestroy();

    /**
     * Destroy power up body.
     */
    void destroy();

    /**
     * PowerUp update method.
     * 
     * @param dt delta time
     */
    void update(float dt);

    /**
     * Draw method of CoinController.
     * 
     * @param batch container to draw on the screen
     */
    void draw(Batch batch);
}
