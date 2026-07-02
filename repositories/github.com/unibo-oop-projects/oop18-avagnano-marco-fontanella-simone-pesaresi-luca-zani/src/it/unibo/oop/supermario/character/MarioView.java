package it.unibo.oop.supermario.character;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * This class creates the textures and sprites of Mario.
 */
public interface MarioView {
    /**
     * Set Mario to display.
     * 
     * @param mario Mario's model
     */
    void setPlayer(Mario mario);

    /**
     * Get the state timer of view.
     * 
     * @return State Timer
     */
    float getStateTimer();

    /**
     * Update Mario's view every frame that passes.
     * 
     * @param dt delta time of the frame
     */
    void update(float dt);

    /**
     * Draw textures and skins of Mario in PlayScreen.
     * 
     * @param batch The container which takes care of drawing every Sprites on the screen.
     */
    void draw(Batch batch);
}
