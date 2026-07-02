package it.unibo.oop.supermario.world;

/**
 * BrickController interface.
 */
public interface BrickController {
    /**
     * If brick is to destroy.
     * 
     * @return true if brick is to destroy
     */
    boolean isToDestroy();

    /**
     * If brick is destroyed.
     * 
     * @return true if brick is destroyed
     */
    boolean isDestroyed();

    /**
     * BrickController update method.
     * 
     * @param dt delta time
     */
    void update(float dt);
}
