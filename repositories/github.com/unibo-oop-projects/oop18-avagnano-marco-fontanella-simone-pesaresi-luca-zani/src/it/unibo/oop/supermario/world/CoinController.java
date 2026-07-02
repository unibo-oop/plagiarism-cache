package it.unibo.oop.supermario.world;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * CoinController interface.
 */
public interface CoinController {
    /**
     * If coin is to destroy.
     * 
     * @return true if coin is to destroy
     */
    boolean isToDestroy();

    /**
     * If coin is destroyed.
     * 
     * @return true if coin is destroyed
     */
    boolean isDestroyed();

    /**
     * Wait 0.4 seconds before the coin disappears.
     */
    void waitToDestroy();

    /**
     * CoinController update method.
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
