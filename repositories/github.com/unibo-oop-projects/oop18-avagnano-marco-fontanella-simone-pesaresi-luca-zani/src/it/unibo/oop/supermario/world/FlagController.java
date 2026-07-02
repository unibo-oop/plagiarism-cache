package it.unibo.oop.supermario.world;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * FlagController interface.
 */
public interface FlagController {
    /**
     * Set the flag down.
     */
    void setFlagDown();

    /**
     * When flag is falling this method plays clear sound.
     */
    void worldClearSound();

    /**
     * Game's ended when flag is completely falled.
     * 
     * @return if is end game
     */
    boolean isEndGame();

    /**
     * Update method of FlagController.
     * 
     * @param dt delta time
     */
    void update(float dt);

    /**
     * If flag is falling.
     * 
     * @return if flag is falling
     */
    boolean isFlagFalling();

    /**
     * Draw method of FlagController.
     * 
     * @param batch container to draw on the screen
     */
    void draw(Batch batch);
}
